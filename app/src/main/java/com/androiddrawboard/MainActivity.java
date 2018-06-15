package com.androiddrawboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SVBar;
import com.larswerkman.holocolorpicker.SaturationBar;
import com.larswerkman.holocolorpicker.ValueBar;

public class MainActivity extends AppCompatActivity {

    DrawBoardView drawBoardView;
    BottomDialog bottomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawBoardView = findViewById(R.id.drawBoardView);
        bottomDialog = new BottomDialog(this, R.layout.btm_dialog, new BottomDialog.OnGetDialogView() {
            @Override
            public void doDialogView(View dialogView) {
                ColorPicker picker = dialogView.findViewById(R.id.picker);
                SVBar svBar = dialogView.findViewById(R.id.svbar);
                OpacityBar opacityBar = dialogView.findViewById(R.id.opacitybar);
                SaturationBar saturationBar = dialogView.findViewById(R.id.saturationbar);
                ValueBar valueBar = dialogView.findViewById(R.id.valuebar);
                picker.addSVBar(svBar);
                picker.addOpacityBar(opacityBar);
                picker.addSaturationBar(saturationBar);
                picker.addValueBar(valueBar);
                int color = picker.getColor();//获取选择器初始颜色
                drawBoardView.setPaintColor(color);
                picker.setOldCenterColor(picker.getColor());
                picker.setOnColorChangedListener(new ColorPicker.OnColorChangedListener() {
                    @Override
                    public void onColorChanged(int color) {
                        drawBoardView.setPaintColor(color);
                    }
                });
                picker.setShowOldCenterColor(false);

            }
        });
    }


    public void draw(View view) {
        bottomDialog.getDialog().show();
    }
}
