package com.campus.william.student.internal.ui.page;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.campus.android_bind.bean.NgGo;
import com.campus.android_bind.bean.NgModel;
import com.campus.android_bind.observer.AllPropertyObserver;
import com.campus.event_filter.callback.ICallback;
import com.campus.event_filter.request.IRequest;
import com.campus.event_filter.request.MODE;
import com.campus.event_filter.response.IResponse;
import com.campus.william.annotationprocessor.annotation.RouterUrl;
import com.campus.william.router.ui.IFragment;
import com.campus.william.student.R;
import com.event_filter.logics.StudentLogicMap;

import java.util.HashMap;

@RouterUrl(state = "studentAuthPage", desc = "学生信息认证页面")
public class StudentAuthFragment extends IFragment {
    private NgModel mNgModel;
    private NgGo mNgGo;
    private View mContainer;

    private String mUserId;

    private AllPropertyObserver mAllPropertyObserver = new AllPropertyObserver(){
        @Override
        public void dataChange(String tag, Object object) {
            super.dataChange(tag, object);
            if(tag.equals("account")){
                mNgModel.addParams("deleteAccount",
                        TextUtils.isEmpty(mNgModel.getString("account")) ? NgGo.NG_VISIBLE.GONE : NgGo.NG_VISIBLE.VISIBLE);
            }else if(tag.equals("password")){
                mNgModel.addParams("deletePassword",
                        TextUtils.isEmpty(mNgModel.getString("password")) ? NgGo.NG_VISIBLE.GONE : NgGo.NG_VISIBLE.VISIBLE);
            }
        }
    };

    @Override
    public void reInit(HashMap bundle) {
        mUserId = (String)bundle.get("userId");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        initNgGo();
        return mContainer;
    }

    private void initNgGo(){
        mNgModel = new NgModel("student");
        mNgGo = new NgGo(getContext(), R.layout.student_auth_page)
                .addNgModel(mNgModel)
                .addAllPropertyObserver(mAllPropertyObserver)
                .addActionContainer(toString());
        mContainer = mNgGo.inflateAndBind();
    }

    public void deleteAccount(View view){
        mNgModel.addParams("account", "");
    }

    public void deletePassword(View view){
        mNgModel.addParams("password", "");
    }

    public void auth(View view){
        String account = mNgModel.getString("account");
        String password = mNgModel.getString("password");

        //TODO 点击了认证页面
        IRequest request = IRequest.obtain();
        request.action(StudentLogicMap.Actions.studentAuthByAccount)
                .add("userId", mUserId)
                .add("account", account)
                .add("password", password)
                .add("schoolId", "schoolId")
                .next(MODE.IO, MODE.UI, new ICallback(){
                    @Override
                    public IRequest next(IResponse response) {
                        if(response.getException() == null){
                            return IRequest.obtain().action(StudentLogicMap.Actions.queryStudentInfo).action("userId", mUserId);
                        }else{
                            return  null;
                        }
                    }
                }).submit(MODE.IO, MODE.UI, new ICallback(){
                    @Override
                    public void done(IResponse response) {
                        super.done(response);
                    }
                });
    }

    private void showToast(String content){
        Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
    }
}
