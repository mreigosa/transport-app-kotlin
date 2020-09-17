package com.mreigar.transportapp.presentation.presenter.transportresourcemap

import com.mreigar.transportapp.domain.executor.Invoker
import com.mreigar.transportapp.domain.executor.Success
import com.mreigar.transportapp.domain.executor.usecase.GetTransportsAroundLocationUseCase
import com.mreigar.transportapp.presentation.mapper.TransportResourceViewEntityMapper
import com.mreigar.transportapp.presentation.presenter.BasePresenter
import java.lang.ref.WeakReference

class TransportResourceMapPresenter(
    view: TransportResourceMapViewTranslator,
    private val getTransportsAroundLocationUseCase: GetTransportsAroundLocationUseCase,
    invoker: Invoker
) : BasePresenter<TransportResourceMapViewTranslator>(WeakReference(view), invoker) {

    private val mapper: TransportResourceViewEntityMapper = TransportResourceViewEntityMapper()

    override fun onReady() {
        super.onReady()
        fetchTransportResources()
    }

    private fun fetchTransportResources() {
        invoker.execute(this, getTransportsAroundLocationUseCase) { result ->
            when (result) {
                is Success -> view()?.showTransportResources(result.data.map { mapper.mapToViewEntity(it) })
                else -> view()?.showError()
            }
        }
    }
}

