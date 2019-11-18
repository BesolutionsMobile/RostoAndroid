package com.besolutions.rosto.NetworkLayar;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Apiclient
{

    /**
     * @API
     *
     * ---> 1) URL OF API METHOD
     *
     * ---> 2) ARRAY OF PARAMETERS KEYS
     *
     */

    LOGIN_USER("user/login/549834453/25598", Arrays.asList("mail", "password"),1),
    Register_Uer("user/register/549834453/25598", Arrays.asList("name", "mail","phone","password"),2),
    Edit_Profile("/edite_profile", Arrays.asList("id_member","city","password","image","phone","name"),3),
    Add_post("/add_post HTTP/", Arrays.asList("id_member","title","des","ciiiiiiiiiiiiiiiiiiiiiiity","category","price","tel","sub","x","x_2","x_3","x_4","x_5","x_6","x_7","x_8","device"),4),
    Main_Ads("/select_haraj",null,5),
    Suggest_Ads("/select_post_suggest_by_department", Collections.singletonList("Department"),6),
    Epire_Ads("/select_expire_post_for_member", Collections.singletonList("id_member"),7),
    Personal_Ads("/select_post_by_id", Collections.singletonList("id_member"),8),
    Search_Ads("/search_app", Collections.singletonList("text_search"),9),
    Select_By_department("/select_haraj_by_Department", Collections.singletonList("Department"),10),
    Select_By_City("/select_haraj_by_search_city", Collections.singletonList("city"),11);





    //--------------------------------------

    /**
     * @BASE_URL
     */

    String BASE_URL = "https://webdesign.be4em.info/rosto_api_ar/";

    private final String URL;
    private final List<String> params;
    private  final int code;



    Apiclient(String URL, List<String> params, int code)
    {

        this.URL = URL;
        this.params = params;
        this.code = code;
}

    public String getURL()
    {
        return BASE_URL + URL;
    }

    public List<String> getParams()
    {
        return params;
    }

    public int getCode()
    {
        return code;
    }
}
