package rekkisoft.trongvu.com.note;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class NewNoteActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_Iconback;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newnote_item);
        init();
    }

    private void init(){
        iv_Iconback = findViewById(R.id.ivicon);
        iv_Iconback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivicon:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_exit,R.anim.anim_back);
                break;
            default:
                break;
        }

    }
}
