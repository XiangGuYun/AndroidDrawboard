package com.androiddrawboard;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public class BottomDialog{

    private View root;
    private Dialog dialog;

    /**
     *
     * @param ctx
     * @param layoutId 对话框布局
     * @param getDialogView 处理对话框中的UI事件
     */
    public BottomDialog(Context ctx, int layoutId, OnGetDialogView getDialogView){
        dialog = new Dialog(ctx, R.style.BottomDialog);
        root = LayoutInflater.from(ctx).inflate(
                layoutId, null);
        getDialogView.doDialogView(root);
        dialog.setContentView(root);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        //dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width =  ctx.getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
    }

    /**
     *
     * @param ctx
     * @param layoutId 对话框布局
     */
    public BottomDialog(Context ctx, int layoutId, OnGetDialog getDialog){
        dialog = new Dialog(ctx, R.style.BottomDialog);
        root = LayoutInflater.from(ctx).inflate(
                layoutId, null);
        getDialog.doDialog(dialog, root);
        dialog.setContentView(root);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        //dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width =  ctx.getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
    }

    public interface OnGetDialogView{
        void doDialogView(View dialogView);
    }

    public interface OnGetDialog{
        void doDialog(Dialog dialog, View dialogView);
    }

    public View getDialogView(){
        return root;
    }

    public Dialog getDialog(){
        return dialog;
    }

}