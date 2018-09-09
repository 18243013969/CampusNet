package com.campus.william.user.internal.ui.page;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.campus.android_bind.bean.NgGo;
import com.campus.android_bind.bean.NgModel;
import com.campus.android_bind.observer.AllPropertyObserver;
import com.campus.william.annotationprocessor.annotation.PageUrl;
import com.campus.william.router.ui.IFragment;
import com.campus.william.user.R;
import com.campus.william.user.internal.ui.widget.BaseDialog;

import java.util.HashMap;

/**
 * Created by wenge on 2018/9/2.
 */
@PageUrl(state = "EditUserInfo", desc = "用户信息编辑页")
public class InfoEditFragment extends IFragment {

    private View mContainer;
    private NgModel mNgModel;
    private NgGo mNaGo;

    private AllPropertyObserver mPropertyObserver = new AllPropertyObserver() {
        @Override
        public void dataChange(String tag, Object object) {
            super.dataChange(tag, object);

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initNgGo();
        return mContainer;
    }

    @Override
    public void reInit(HashMap bundle) {

    }

    private void initNgGo() {
        mNgModel = new NgModel("infoEdit");
        mNaGo = new NgGo(getActivity(), R.layout.layout_info_edit)
                .addNgModel(mNgModel)
                .addAllPropertyObserver(mPropertyObserver)
                .addActionContainer(this);
        mContainer = mNaGo.inflateAndBind();
    }

    /**
     * 点击返回，并保存或者是上传相关的数据
     **/
    public void back(View view) {

    }

    public void addPhoto(View view) {
        final ImageView imageView = (ImageView) view;
        final int id = imageView.getId();
        if (id == R.id.user_infoEdit_background_wall_image_1) {

        } else if (id == R.id.user_infoEdit_background_wall_image_2) {

        } else if (id == R.id.user_infoEdit_background_wall_image_3) {

        } else if (id == R.id.user_infoEdit_background_wall_image_4) {

        } else if (id == R.id.user_infoEdit_background_wall_image_5) {

        } else if (id == R.id.user_infoEdit_background_wall_image_6) {
          //
        }

    }

    private void setInputDialog() {
        View inflate = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_nickname_edit, null);
        final BaseDialog baseDialog = new BaseDialog(getActivity(), R.style.ActionSheetDialogStyle);
        final EditText nickNameEditText = inflate.findViewById(R.id.input_info_dialog_edit);
        final Button okBtn = inflate.findViewById(R.id.btn_ok);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.dismiss();
            }
        });
        baseDialog.setContentView(inflate);
        baseDialog.setGravity();
        baseDialog.show();

    }
}
