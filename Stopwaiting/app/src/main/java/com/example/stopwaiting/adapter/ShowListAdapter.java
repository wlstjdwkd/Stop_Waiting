package com.example.stopwaiting.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.example.stopwaiting.R;
import com.example.stopwaiting.activity.DataApplication;
import com.example.stopwaiting.activity.ShowListActivity;
import com.example.stopwaiting.dto.UserInfo;
import com.example.stopwaiting.dto.WaitingInfo;
import com.example.stopwaiting.dto.WaitingQueue;
import com.example.stopwaiting.viewholder.ShowListViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowListAdapter extends RecyclerView.Adapter<ShowListViewHolder> {
    private Context mContext;
    private List<WaitingInfo> mItemList;
    private WaitingQueue mWaitingQueue;
    private int myNum = 0;

    public ShowListAdapter(Context a_context, List<WaitingInfo> a_itemList) {
        mContext = a_context;
        mItemList = a_itemList;
    }

    public interface OnItemClickEventListener {
        void onItemClick(String name);
    }

    private OnItemClickEventListener mItemClickListener = new OnItemClickEventListener() {
        @Override
        public void onItemClick(String name) {

            Intent data = new Intent();
            data.putExtra("name", name);
            data.putExtra("case", 1);

            ShowListActivity.showListActivity.setResult(Activity.RESULT_OK, data);
            ShowListActivity.showListActivity.finish();
        }
    };

    @NonNull
    @Override
    public ShowListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.waiting_item, parent, false);
        return new ShowListViewHolder(view, mItemClickListener).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowListViewHolder holder, int position) {
        final WaitingInfo waitingItem = mItemList.get(position);

        if (waitingItem.getUrlList().get(0) == null) {
            Glide.with(mContext.getApplicationContext())
                    .load(Uri.parse(waitingItem.getUrlList().get(0)))
                    .into(holder.imgItem);
        } else {
            Glide.with(mContext.getApplicationContext())
                    .load(waitingItem.getUrlList().get(0))
                    .into(holder.imgItem);
        }
        holder.txtName.setText(waitingItem.getName());
        holder.txtLocDetail.setText(waitingItem.getLocDetail());

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String nowTime = sdf.format(new Date(System.currentTimeMillis()));

        if (waitingItem.getType().equals("NORMAL")) {
            queueRequest(waitingItem.getWaitingId(), "NORMAL");
            myNum = mWaitingQueue.getWaitingPersonList().size();

            if (myNum < 1) myNum = 0;
            holder.txtWaitCnt.setText("대기중인 인원 :  " + String.valueOf(myNum) + " 명");
        } else {
            for (String time : waitingItem.getTimetable()) {
                if (((DataApplication) mContext.getApplicationContext()).firstIsLater(time, nowTime)) {
                    queueRequest(waitingItem.getWaitingId(), time);
                    myNum = mWaitingQueue.getWaitingPersonList().size();

                    if (myNum < 1) myNum = 0;
                    holder.txtWaitCnt.setText("대기중인 인원 :  " + String.valueOf(myNum) + " 명");
                    break;
                }
            }

            holder.txtWaitCnt.setText("예약 가능한 시간대가 없습니다.");
        }
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public void queueRequest(Long selectWID, String time) {
        if (DataApplication.isTest) {
            for (WaitingQueue temp : DataApplication.testWaitingQueueDBList) {
                if (temp.getWId().equals(selectWID) && temp.getTime().equals(time)) {
                    mWaitingQueue = temp;
                }
            }
        } else {
            Log.e("URL", DataApplication.serverURL + "/waitinginfo/" + selectWID + "/queue?time=" + time);
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
