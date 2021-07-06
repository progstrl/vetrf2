package com.gmail.progstrl.vetrf2.main

import android.util.SparseArray
import androidx.collection.ArrayMap
import androidx.lifecycle.MutableLiveData
import androidx.multidex.MultiDexApplication
import com.gmail.progstrl.vetrf2.database.BaseName
import com.gmail.progstrl.vetrf2.database.DatabaseVetrf

//import com.yandex.metrica.YandexMetrica;
//import com.yandex.metrica.YandexMetricaConfig;
class MyApplication : MultiDexApplication() {
    //    apikey app metrika 8d106e2c-553f-4d5b-b51e-311e6a38db2d
    private val API_key = "8d106e2c-553f-4d5b-b51e-311e6a38db2d"
    var dateResolveDiscrepancy: Long = 0
    var vetrfRequestServiceIsWorking = false
        private set

    override fun onCreate() {
        super.onCreate()
        myApplication = this
        // DB = DatabaseVetrf.getDatabase(getApplicationContext());
        // SettingActivity.setDefaultArrayMap(mapSetting);

//        // Creating an extended library configuration.
//        YandexMetricaConfig config = YandexMetricaConfig.newConfigBuilder(API_key).build();
//        // Initializing the AppMetrica SDK.
//        YandexMetrica.activate(getApplicationContext(), config);
//        // Automatic tracking of user activity.
//        YandexMetrica.enableActivityAutoTracking(this);
    }

    //private String baseName;
    var userNameSelected: String? = null
        get() = if (field == null) {
            ""
        } else field
    var enterpriseSelectedGuid: String? = null

    //        if (baseSelected != null) {
//            this.baseName = baseSelected.getName();
//        }
    var baseSelected: BaseName? = null
        get() = if (field == null) {
            BaseName("nullBase", "nullGuid")
        } else field
    private val apiKeyResponse: String? = null
    private val issuerIdResponse: String? = null
    private val applicationIdResponse: String? = null

    //    public String getApiKeyResponse() {
    //        return apiKeyResponse;
    //    }
    //
    //    public void setApiKeyResponse(String apiKeyResponse) {
    //        this.apiKeyResponse = apiKeyResponse;
    //    }
    //    public String getIssuerIdResponse() {
    //        return issuerIdResponse;
    //    }
    //
    //    public void setIssuerIdResponse(String issuerIdResponse) {
    //        this.issuerIdResponse = issuerIdResponse;
    //    }
    //    public String getApplicationIdResponse() {
    //        return applicationIdResponse;
    //    }
    //
    //    public void setApplicationIdResponse(String applicationIdResponse) {
    //        this.applicationIdResponse = applicationIdResponse;
    //    }
    private var mapBase: SparseArray<*> = SparseArray<Any?>()
    private var mapApiKey: SparseArray<*> = SparseArray<Any?>()
    private var mapUserAutoExchange: SparseArray<*> = SparseArray<Any?>()
    private var mapIsTesting: SparseArray<*> = SparseArray<Any?>()
    private var mapEnterprise =
        ArrayMap<String, SparseArray<*>>()
    private var mapBorderDate =
        ArrayMap<String, ArrayMap<String, Long>>()
    private var cashBase =
        ArrayMap<String, BaseName>()
    private var mapSetting =
        ArrayMap<String, Long>()
    private val DB: DatabaseVetrf? = null
    private val serviceWorking = MutableLiveData<Boolean>()
    fun getServiceWorking(): MutableLiveData<Boolean> {
        return serviceWorking
    }

    private var needRequestConfirmedVsdByUuid = false
    private var needFullLoadJournal = false
    private var needResolveDiscrepancy = false // Устранения Несоответствия
    private var AutoWriteOffResolveDiscrepancy = false
    private val logResolveDiscrepancy =
        MutableLiveData<String>()
    private val logString = ""
    private var mainActivityClosed = false
    private var vetrfRequestServiceIsProcessing = false
    fun getVetrfRequestServiceIsProcessing(): Boolean {
        return vetrfRequestServiceIsProcessing
    }

    fun setVetrfRequestServiceIsProcessing(vetrfRequestServiceIsProcessing: Boolean) {
        this.vetrfRequestServiceIsProcessing = vetrfRequestServiceIsProcessing
    }

    fun getMainActivityClosed(): Boolean {
        return mainActivityClosed
    }

    fun setMainActivityClosed(mainActivityClosed: Boolean) {
        this.mainActivityClosed = mainActivityClosed
    }

    fun getLogResolveDiscrepancy(): MutableLiveData<String> {
        return logResolveDiscrepancy
    }

    fun setLogResolveDiscrepancy(value: String) {
//        logString = value + logString ;
//        this.logResolveDiscrepancy.postValue(logString);
        logResolveDiscrepancy.postValue(value)
    }

    fun isAutoWriteOffResolveDiscrepancy(): Boolean {
        return AutoWriteOffResolveDiscrepancy
    }

    fun setAutoWriteOffResolveDiscrepancy(autoWriteOffResolveDiscrepancy: Boolean) {
        AutoWriteOffResolveDiscrepancy = autoWriteOffResolveDiscrepancy
    }

    fun isNeedResolveDiscrepancy(): Boolean {
        return needResolveDiscrepancy
    }

    fun setNeedResolveDiscrepancy(needResolveDiscrepancy: Boolean) {
        this.needResolveDiscrepancy = needResolveDiscrepancy
    }

    fun isNeedFullLoadJournal(): Boolean {
        return needFullLoadJournal
    }

    fun setNeedFullLoadJournal(needFullLoadJournal: Boolean) {
        this.needFullLoadJournal = needFullLoadJournal
    }

    fun setServiceWorking(serviceWorking: Boolean) {
        vetrfRequestServiceIsWorking = serviceWorking
        this.serviceWorking.value = serviceWorking
    }

    fun getMapSetting(): ArrayMap<String, Long> {
        return mapSetting
    }

    fun setMapSetting(mapSetting: ArrayMap<String, Long>) {
        this.mapSetting = mapSetting
    }

    fun getCashBase(): ArrayMap<String, BaseName> {
        return cashBase
    }

    fun setCashBase(cashBase: ArrayMap<String, BaseName>) {
        this.cashBase = cashBase
    }

    //    public String getBaseName() {
    //        return baseName;
    //    }
    fun getDB(): DatabaseVetrf? {
        return DB
    }

//    fun setMapBase(mapBase: SparseArray<*>) {
//        field = mapBase
//    }

    fun getMapApiKey(): SparseArray<*> {
        return mapApiKey
    }

    fun setMapApiKey(mapApiKey: SparseArray<*>) {
        this.mapApiKey = mapApiKey
    }

    fun getMapEnterprise(): ArrayMap<*, *> {
        return mapEnterprise
    }

//    fun setMapEnterprise(mapEnterprise: ArrayMap<*, *>) {
//        this.mapEnterprise = mapEnterprise
//    }

    fun getMapBorderDate(): ArrayMap<String, ArrayMap<String, Long>> {
        return mapBorderDate
    }

    fun setMapBorderDate(mapBorderDate: ArrayMap<String, ArrayMap<String, Long>>) {
        this.mapBorderDate = mapBorderDate
    }

    fun getMapUserAutoExchange(): SparseArray<*> {
        return mapUserAutoExchange
    }

    fun setMapUserAutoExchange(mapUserAutoExchange: SparseArray<*>) {
        this.mapUserAutoExchange = mapUserAutoExchange
    }

    fun getMapIsTesting(): SparseArray<*> {
        return mapIsTesting
    }

    fun setMapIsTesting(mapIsTesting: SparseArray<*>) {
        this.mapIsTesting = mapIsTesting
    }

    fun isNeedRequestConfirmedVsdByUuid(): Boolean {
        return needRequestConfirmedVsdByUuid
    }

    fun setNeedRequestConfirmedVsdByUuid(needRequestConfirmedVsdByUuid: Boolean) {
        this.needRequestConfirmedVsdByUuid = needRequestConfirmedVsdByUuid
    }

    companion object {
        private var myApplication: MyApplication? = null
        fun get(): MyApplication? {
            return myApplication
        }
    }
}