package com.example.donors.data

class PlasmaData( _number : String , _blood : String , _locale :String ){

    // number , locale , group

    private val email : String = CurrentUserInfo.getEmail()
    private var number : String
    private var blood : String
    private var locale : String
    private val uid : String = CurrentUserInfo.getUID()

    init{
        number = _number
        blood = _blood
        locale = _locale
    }

    @JvmName("get")
    fun get() : MutableList<String> = mutableListOf<String>( uid , email , number , blood , locale )

    @JvmName("set")
    fun set( _number : String , _blood : String , _locale: String ){
        this.blood = _blood
        this.number = _number
        this.locale = _locale
    }

    fun setNumber(_number: String) {
        this.number = _number;
    }

    fun setBlood(_blood: String){
        this.blood = _blood;
    }

    fun setLocale(_locale: String){
        this.locale = _locale;
    }

    fun getEmail():String = this.email

    fun getNumber():String = this.number

    fun getLocale():String = this.locale

    fun getBlood():String = this.blood

    fun getUid():String = this.uid

}