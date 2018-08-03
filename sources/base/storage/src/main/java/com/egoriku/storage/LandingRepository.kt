package com.egoriku.storage

import com.egoriku.core.models.ILandingModel
import com.egoriku.core.repository.ILandingRepository
import io.reactivex.Observable

class LandingRepository : ILandingRepository {

    override fun getLandingInfo(): Observable<ILandingModel> {
        return Observable.create { emitter ->
            emitter.onNext(LandingModel(
                    aboutInfo = "Женщина должна притягивать своей красотой взгляды окружающих. Красивый аксессуар всегда был неотъемлемой частью гардероба. Он подчеркивал женскую загадочность и робкость... \n" +
                            "\n" +
                            "В последнее время изделия из натуральных материалов стали популярны на рынке. Все обусловленно тем, что они делаются вручную и, в конечном итоге, являются уникальным предметом. Компания \"Дамское счастье\" сделает все, чтобы вы почувствовали себя той самой неповторимой."

            ))
        }
    }
}