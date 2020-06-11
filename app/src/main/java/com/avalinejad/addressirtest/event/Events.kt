package com.avalinejad.addressirtest.event

class NetworkError(val throwable: Throwable, val onRetry: (() -> Unit)? = null, val onCancle: (() -> Unit)? = null)
