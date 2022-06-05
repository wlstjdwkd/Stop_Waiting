package com.example.stopwaiting.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.stopwaiting.R;
import com.example.stopwaiting.databinding.WaitingNormalBinding;
import com.example.stopwaiting.dto.ImgItem;
import com.example.stopwaiting.dto.UserInfo;
import com.example.stopwaiting.dto.WaitingInfo;
import com.example.stopwaiting.dto.WaitingQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WaitingNormalActivity extends AppCompatActivity {
    private int pivot, mStatusCode;
    private ArrayList<ImgItem> imgItems;
    private ArrayList<String> urlItems;
    private WaitingInfo mWaitingInfo;
    private WaitingQueue mWaitingQueue;

    private WaitingNormalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = WaitingNormalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();


        mWaitingInfo = new WaitingInfo();
        for (int i = 0; i < DataApplication.waitingList.size(); i++) {
            if (DataApplication.waitingList.get(i).getWaitingId().equals(intent.getLongExtra("id", 0))) {
                mWaitingInfo = DataApplication.waitingList.get(i);
            }
        }

        mWaitingQueue = new WaitingQueue();
        queueRequest(mWaitingInfo.getWaitingId(), mWaitingInfo.getType());
        imageRequest();

        pivot = 0;
        String content = "";
        if (imgItems.size() > 0) {
            for (int i = 0; i < imgItems.size(); i++) {
                content = content + "·";
            }
            binding.txtImgCnt.setText(content);
            setImg();
        } else {
            content = "·";
            binding.txtImgCnt.setText(content);
            binding.imageView.setImageResource(R.drawable.empty_icon);
        }

        binding.txtWaitingName.setText(mWaitingInfo.getName());
        binding.txtLocDeatail.setText(mWaitingInfo.getLocDetail());
        binding.txtInfo.setText(mWaitingInfo.getInfo());

        binding.btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imgItems.size() > 1) {
                    if (pivot > 0) {
                        pivot--;
                    } else {
                        pivot = imgItems.size() - 1;
                    }
                    setImg();
                }
            }
        });

        binding.btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imgItems.size() > 1) {
                    if (pivot < imgItems.size() - 1) {
                        pivot++;
                    } else {
                        pivot = 0;
                    }
                    setImg();
                }
            }
        });

        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waitingRequest();
            }
        });
    }

    public void setImg() {
        String content = binding.txtImgCnt.getText().toString();
        SpannableString spannableString = new SpannableString(content);

        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF6702")), pivot, pivot + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), pivot, pivot + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.txtImgCnt.setText(spannableString);

        Glide.with(getApplicationContext())
                .load(imgItems.get(pivot).getUri())
                .into(binding.imageView);

    }

    public void waitingRequest() {
        if (DataApplication.isTest) {
            for (int i = 0; i < ((DataApplication) getApplication()).testWaitingQueueDBList.size(); i++) {
                WaitingQueue temp = ((DataApplication) getApplication()).testWaitingQueueDBList.get(i);
                if (temp.getQueueName().equals(binding.txtWaitingName.getText()) && temp.getTime().equals("NORMAL")) {
                    if (temp.getWaitingPersonList() != null) {
                        int check = temp.addWPerson(((DataApplication) getApplication()).currentUser);
                        switch (check) {
                            case 0:
                                ((DataApplication) getApplication()).testWaitingQueueDBList.set(i, temp);
                                finish();
                                break;
                            case 1:
                                Toast.makeText(getApplicationContext(), "이미 등록한 웨이팅입니다.", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(getApplicationContext(), "최대 인원인 웨이팅입니다.", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                    break;
                }
            }
        } else {
            JSONObject jsonBodyObj = new JSONObject();
            try {
                jsonBodyObj.put("studentCode", DataApplication.currentUser.getStudentCode());
                jsonBodyObj.put("waitingId", mWaitingInfo.getWaitingId());
                jsonBodyObj.put("type", mWaitingInfo.getType());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            final String requestBody = String.valueOf(jsonBodyObj.toString());

            StringRequest request = new StringRequest(Request.Method.GET, DataApplication.serverURL + "/addwaiting",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String jsonObject) {
                            int statusCode = mStatusCode;
                            switch (statusCode) {
                                case HttpURLConnection.HTTP_OK:
                                    Toast.makeText(getApplicationContext(), "정상 등록되었습니다.", Toast.LENGTH_SHORT).show();
                                    finish();
                                    break;
                                case HttpURLConnection.HTTP_NOT_FOUND:
                                    Toast.makeText(getApplicationContext(), "이미 대기중인 웨이팅입니다.", Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpURLConnection.HTTP_INTERNAL_ERROR:
                                    Toast.makeText(getApplicationContext(), "최대 인원인 웨이팅입니다.", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "등록에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }

                @Override
                public byte[] getBody() {
                    try {
                        if (requestBody != null && requestBody.length() > 0 && !requestBody.equals("")) {
                            return requestBody.getBytes("utf-8");
                        } else {
                            return null;
                        }
                    } catch (UnsupportedEncodingException uee) {
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    if (response != null) {
                        mStatusCode = response.statusCode;
                    }
                    return super.parseNetworkResponse(response);
                }
            };

            request.setShouldCache(false);
            DataApplication.requestQueue.add(request);
        }
    }

    public void imageRequest() {
        imgItems = new ArrayList<>();
        for (int i = 0; i < mWaitingInfo.getUrlList().size(); i++) {
            ImgItem tempImg = new ImgItem();
            tempImg.setSUri(mWaitingInfo.getUrlList().get(i));

            imgItems.add(tempImg);
        }
    }

    public void queueRequest(Long selectWID, String time) {
        if (DataApplication.isTest) {
            for (WaitingQueue temp : ((DataApplication) getApplication()).testWaitingQueueDBList) {
                if (temp.getWId().equals(selectWID) && temp.getTime().equals("NORMAL")) {
                    if (temp.getWaitingPersonList() != null) {
                        binding.txtWaitCnt.setText("현재 " + String.valueOf(temp.getWaitingPersonList().size()) + "명 대기중");
                    } else {
                        binding.txtWaitCnt.setText("현재 대기 인원이 없습니다.");
                    }
                    break;
                }
            }
        } else {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, DataApplication.serverURL + "/waitinginfo/" + selectWID + " /queue?time=" + time, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            try {
                                JSONArray dataArray = jsonObject.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {
                                    JSONObject dataObject = dataArray.getJSONObject(i);

                                    WaitingQueue data = new WaitingQueue();

                                    data.setQueueName(dataObject.getString("queueName"));
                                    data.setQId(dataObject.getLong("queueId"));
                                    data.setTime(dataObject.getString("time"));
                                    data.setMaxPerson(dataObject.getInt("maxPerson"));

                                    ArrayList<UserInfo> tempUserList = new ArrayList<>();
                                    JSONArray userArray = jsonObject.getJSONArray("waitingPersonList");
                                    for (int j = 0; j < userArray.length(); j++) {
                                        JSONObject userObject = dataArray.getJSONObject(i);

                                        UserInfo tempUser = new UserInfo();
                                        tempUser.setStudentCode(userObject.getLong("id"));

                                        tempUserList.add(tempUser);
                                    }
                                    data.setWaitingPersonList(tempUserList);

                                    mWaitingQueue = data;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "등록에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }

            };

            request.setShouldCache(false);
            DataApplication.requestQueue.add(request);

        }
    }
}
