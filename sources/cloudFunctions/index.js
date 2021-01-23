const functions = require("firebase-functions");

const admin = require("firebase-admin");
admin.initializeApp();

const db = admin.firestore();

/**
 * Deploy command:
 *
 *  firebase deploy --only functions
 *
 */

/**
 * Cloud function, increment count of specific category.
 */
exports.subcategoryOnCreate = functions.firestore
    .document("listOfHats/{documentID}")
    .onCreate((change, context) => {
      return extracted(change, OperationType.CREATE);
    });

/**
 * Cloud function, decrement count of specific category.
 */
exports.subcategoryOnDelete = functions.firestore
    .document("listOfHats/{documentID}")
    .onDelete((change, context) => {
      return extracted(change, OperationType.DELETE);
    });

const OperationType = {
  CREATE: 1,
  DELETE: 2,
};

// eslint-disable-next-line require-jsdoc
function extracted(change, operationType) {
  const data = change.data();

  // eslint-disable-next-line no-prototype-builtins
  const isHasCategoryId = data.hasOwnProperty("categoryId");
  // eslint-disable-next-line no-prototype-builtins
  const isHasSubcategoryId = data.hasOwnProperty("subCategoryId");
  if (!isHasCategoryId) {
    console.log("egorik: ", "categoryId null");
    return;
  }
  if (!isHasSubcategoryId) {
    console.log("egorik: ", "subcategoryId null");
    return;
  }

  const {categoryId, subCategoryId} = data;
  console.log("egorik: ", categoryId, subCategoryId);

  db.collection("subcategories")
      .where("categoryId", "==", categoryId)
      .where("subCategoryId", "==", subCategoryId)
      .get()
      .then(function(querySnapshot) {
        const documentSnapshot = querySnapshot.docs[0];
        // eslint-disable-next-line max-len
        let currentCount = documentSnapshot._fieldsProto.publishedCount.integerValue;
        console.log("egorik: currentCount=", currentCount);

        let newCount;
        if (operationType === OperationType.CREATE) {
          console.log("egorik: operation type = Create");
          newCount = ++currentCount;
        } else if (operationType === OperationType.DELETE) {
          console.log("egorik: operation type = Delete");
          newCount = --currentCount;
        }

        console.log("egorik: newCount =", newCount);
        console.log("egorik: id =", documentSnapshot.id);

        return db.collection("subcategories")
            .doc(documentSnapshot.id)
            .set({publishedCount: newCount}, {merge: true});
      })
      .catch(function(error) {
        console.log("egorik: Error getting documents ", error);
      });
}
