package nullteam.richeditortest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;

import jp.wasabeef.richeditor.RichEditor;
import nullteam.richeditortest.utils.EditorUtil.Editor;

public class MainActivity extends AppCompatActivity {
    private RichEditor mEditor;
    private Editor editor;
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    private Uri imageUri;
    public File outputImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        //隐藏actionBar
        if (actionBar != null) {
            actionBar.hide();

            editor = new Editor();
            mEditor = (RichEditor) findViewById(R.id.editor);
            //编辑设置高度
            mEditor.setEditorHeight(800);
            //设置字体大小
            mEditor.setEditorFontSize(22);
            //设置字体颜色
            mEditor.setEditorFontColor(Color.BLACK);
            mEditor.setPadding(10, 10, 10, 10);
            //背景文字
            mEditor.setPlaceholder("Insert text here...");


            //mPreview = (TextView) findViewById(R.id.preview);
//            mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
//                @Override
//                public void onTextChange(String text) {
//                    mPreview.setText(text);
//                }
//           });

            findViewById(R.id.action_undo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.undo();
                }
            });

            findViewById(R.id.action_redo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.redo();
                }
            });

            findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.setBold();
                }
            });

            findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.setItalic();
                }
            });

            findViewById(R.id.action_subscript).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.setSubscript();
                }
            });

            findViewById(R.id.action_superscript).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.setSuperscript();
                }
            });

            findViewById(R.id.action_strikethrough).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.setStrikeThrough();
                }
            });

            findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.setUnderline();
                }
            });

            findViewById(R.id.action_heading1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.setHeading(1);
                }
            });

            findViewById(R.id.action_heading2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.setHeading(2);
                }
            });

            findViewById(R.id.action_heading3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.setHeading(3);
                }
            });

            findViewById(R.id.action_heading4).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.setHeading(4);
                }
            });

            findViewById(R.id.action_heading5).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.setHeading(5);
                }
            });

            findViewById(R.id.action_heading6).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.setHeading(6);
                }
            });

            findViewById(R.id.action_txt_color).setOnClickListener(new View.OnClickListener() {
                private boolean isChanged;

                @Override
                public void onClick(View v) {
                    mEditor.setTextColor(isChanged ? Color.BLACK : Color.RED);
                    isChanged = !isChanged;
                }
            });

            findViewById(R.id.action_bg_color).setOnClickListener(new View.OnClickListener() {
                private boolean isChanged;

                @Override
                public void onClick(View v) {
                    mEditor.setTextBackgroundColor(isChanged ? Color.TRANSPARENT : Color.YELLOW);
                    isChanged = !isChanged;
                }
            });

            findViewById(R.id.action_indent).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.setIndent();
                }
            });

            findViewById(R.id.action_outdent).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.setOutdent();
                }
            });

            findViewById(R.id.action_align_left).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.setAlignLeft();
                }
            });

            findViewById(R.id.action_align_center).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.setAlignCenter();
                }
            });

            findViewById(R.id.action_align_right).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.setAlignRight();
                }
            });

            findViewById(R.id.action_blockquote).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.setBlockquote();
                }
            });

            findViewById(R.id.action_insert_bullets).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.setBullets();
                }
            });

            findViewById(R.id.action_insert_numbers).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.setNumbers();
                }
            });

            findViewById(R.id.action_insert_image).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //创建File对象，用于存储拍照后的图片
                    outputImage = editor.outputImage(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");

                    //7.0以上，使用FileProvider.getUriForFile调用Uri地址
                    if (Build.VERSION.SDK_INT >= 24) {
                        imageUri = FileProvider.getUriForFile(MainActivity.this, "nullteam.richeditortest.fileprovider", outputImage);
                    } else {
                        imageUri = Uri.fromFile(outputImage);
                    }

                    //启动相机程序
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, TAKE_PHOTO);
                }
            });

            findViewById(R.id.action_insert_camera).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //检查权限
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        //申请sd卡运行权限
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    } else {
                        editor.openAlbum(MainActivity.this);
                    }
                }
            });

        }
    }

    //信息反馈
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    //将拍摄的照片显示出来
                    String outputImagePath = outputImage.getPath();
                    mEditor.insertImage(outputImagePath + "\" style=\"width:100%;", "alt");
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        //4.4及以上的系统使用方法处理图片
                        editor.handleImageOnKitKat(data,outputImage,getExternalCacheDir(),System.currentTimeMillis() + ".jpg",mEditor,this);
                    } else {
                        //4.4以下系统使用这个方法处理图片
                       editor.handleImageBeforeKitKat(data,outputImage,getExternalCacheDir(),System.currentTimeMillis() + ".jpg",mEditor,this);
                    }
                    break;
                }
            default:
                break;
        }
    }

}