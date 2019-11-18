package com.besolutions.rosto.NetworkLayar;

import com.android.volley.VolleyError;

/**
 *
 * @desc Java Interface Response Callback
 */

public interface NetworkInterface
{
    void OnStart();
    void OnResponse(ResponseModel model);
    void OnError(VolleyError error);
}
