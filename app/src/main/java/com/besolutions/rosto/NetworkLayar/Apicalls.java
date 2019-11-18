package com.besolutions.rosto.NetworkLayar;

import android.content.Context;

import com.android.volley.Request;

import org.json.JSONException;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @desc Java Api Calls Contains all the Project Calls
 */

public class Apicalls
{

    private APIRouter apiRouter;

    public Apicalls(Context context, NetworkInterface networkInterface)
    {

        apiRouter = new APIRouter(context, networkInterface);
    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func User Login
     */

    public void loginUser(final String email, final String password) {

        apiRouter.performRequest(Apiclient.LOGIN_USER.getURL(),Apiclient.LOGIN_USER.getParams(), Arrays.asList(email,password),Request.Method.POST,Apiclient.LOGIN_USER.getCode());

    }


    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func User Registration
     */

    public void registerUser(final String Name, final String Email, final String Password, final String Phone)
    {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.Register_Uer.getURL(), Request.Method.POST, Apiclient.Register_Uer.getParams(), Arrays.asList(Name,Email,Password,Phone), null);
        } catch (
                JSONException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------------------------------------



    /**
     *
     * @func Edit User Profile
     */

    public  void editProfile (final String Id_Number, final String City, final String Password, final String Image, final String Phone, final String Name )
    {

        apiRouter.performRequest(Apiclient.Edit_Profile.getURL(),Apiclient.Edit_Profile.getParams(), Arrays.asList(Id_Number,City,Password,Image,Phone,Name),Request.Method.POST,Apiclient.Edit_Profile.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Add a new Ad
     */

    public  void addPost (final String Id_Number, final String Title, final String Des, final String Cityyyyy, final String Category, final String Price, final String Tel, final String Sub, final String X, final String X2, final String X3, final String X4, final String X5, final String X6, final String X7, final String X8, final String Device)
    {

        apiRouter.performRequest(Apiclient.Add_post.getURL(),Apiclient.Add_post.getParams(), Arrays.asList(Id_Number,Title,Des,Cityyyyy,Category,Price,Tel,Sub,X,X2,X3,X4,X5,X6,X7,X8,Device),Request.Method.POST,Apiclient.Add_post.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Main Activity Ads
     *
     */

    public void mainAds ()
    {

        apiRouter.performRequest(Apiclient.Main_Ads.getURL(),Apiclient.Main_Ads.getParams(),null,Request.Method.POST, Apiclient.Main_Ads.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Main Activity Ads
     *
     */


    public void suggestAds (final String Department)
    {

        apiRouter.performRequest(Apiclient.Suggest_Ads.getURL(),Apiclient.Suggest_Ads.getParams(), Collections.singletonList(Department),Request.Method.POST,Apiclient.Suggest_Ads.getCode());

    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Main Activity Ads
     *
     */

    public void epireAds (final String Id_Number)
    {

        apiRouter.performRequest(Apiclient.Epire_Ads.getURL(),Apiclient.Epire_Ads.getParams(), Collections.singletonList(Id_Number),Request.Method.POST,Apiclient.Epire_Ads.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Main Activity Ads
     *
     */

    public void personalAds (final String Id_Number)
    {

        apiRouter.performRequest(Apiclient.Personal_Ads.getURL(),Apiclient.Personal_Ads.getParams(), Collections.singletonList(Id_Number),Request.Method.POST,Apiclient.Personal_Ads.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Main Activity Ads
     *
     */

    public void searchAds (final String Text_Search)
    {

        apiRouter.performRequest(Apiclient.Search_Ads.getURL(),Apiclient.Search_Ads.getParams(), Collections.singletonList(Text_Search),Request.Method.POST,Apiclient.Search_Ads.getCode());

    }

    //----------------------------------------------------------------------------------------------




    public void selectByDepartment (final String Department)
    {

        apiRouter.performRequest(Apiclient.Select_By_department.getURL(),Apiclient.Select_By_department.getParams(), Collections.singletonList(Department),Request.Method.POST,Apiclient.Select_By_department.getCode());

    }

    //----------------------------------------------------------------------------------------------




    public void select_By_City(final String City)
    {

        apiRouter.performRequest(Apiclient.Select_By_City.getURL(),Apiclient.Select_By_City.getParams(), Collections.singletonList(City),Request.Method.POST,Apiclient.Select_By_City.getCode());

    }

    //----------------------------------------------------------------------------------------------
}