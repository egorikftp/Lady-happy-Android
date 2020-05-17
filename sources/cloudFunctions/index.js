const functions = require('firebase-functions');

const admin = require('firebase-admin');
admin.initializeApp();

const db = admin.firestore();

/**
 * Cloud function, increment count of specific category.
 */
exports.subcategoryOnCreate = functions.firestore
    .document('listOfHats/{documentID}')
    .onCreate((change, context) => {
        return extracted(change, OperationType.CREATE);
    });

/**
 * Cloud function, decrement count of specific category.
 */
exports.subcategoryOnDelete = functions.firestore
    .document('listOfHats/{documentID}')
    .onDelete((change, context) => {
        return extracted(change, OperationType.DELETE)
    })

const OperationType = {
    CREATE: 1,
    DELETE: 2
};

function extracted(change, operationType) {
    const data = change.data()

    const isHasCategoryId = data.hasOwnProperty('categoryId')
    const isHasSubcategoryId = data.hasOwnProperty('subcategoryId')

    console.log("egorik: ", isHasCategoryId, isHasSubcategoryId)

    if (!isHasCategoryId || !isHasSubcategoryId) {
        console.log("egorik: ", "categoryId or subcategoryId null")
        return;
    }

    const categoryId = data.categoryId
    const subcategoryId = data.subcategoryId
    console.log("egorik: ", categoryId, subcategoryId)

    db.collection('subcategories')
        .where('categoryId', '==', categoryId)
        .where('subcategoryId', '==', subcategoryId)
        .get()
        .then(function (querySnapshot) {
            const documentSnapshot = querySnapshot.docs[0];
            let currentCount = documentSnapshot._fieldsProto.publishedCount.integerValue
            console.log("egorik: currentCount=", currentCount)

            let newCount;
            if (operationType === OperationType.CREATE) {
                console.log("egorik: operation type = Create")
                newCount = ++currentCount
            } else if (operationType === OperationType.DELETE) {
                console.log("egorik: operation type = Delete")
                newCount = --currentCount
            }

            console.log("egorik: newCount=", newCount)
            console.log("egorik: id=", documentSnapshot.id)

            return db.collection('subcategories')
                .doc(documentSnapshot.id)
                .set({publishedCount: newCount}, {merge: true})
        })
        .catch(function (error) {
            console.log("egorik: Error getting documents ", error);
        });
}
