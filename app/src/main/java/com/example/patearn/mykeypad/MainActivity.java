package com.example.patearn.mykeypad;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import me.grantland.widget.AutofitLayout;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    EditText editText;
    String textTemp;
    String textTemp2;
    String frontText;
    AutofitLayout autofitLayout;
    String fullStringOfEditText;
    String stringToDisplay = "";
    String stringToDisplayFront;
    String[] parsedText;
    ImageView leftArrow;
    ImageView rightArrow;
    final String sampleText1="이랬다 저랬다 왔다갔다 개미들만 새됐어 계약 후 취소 사례도 있어";
    final String sampleTextDescrip1="[출처] 머니투데이 이해인 기자 | 입력 : 2015.10.20 16:21 \n http://news.mt.co.kr/mtview.php?no=2015102015165262697";
    final String sampleText2="나는 기대감이 있었는데 사랑이는 정말 기대를 했었는지 몰라서 걱정이었다";
    final String sampleTextDescrip2="[출처] 뉴스1코리아 장아름 기자 | 입력 : 2017-09-30 | \n https://www.dispatch.co.kr/927023";
    final String sampleText3="홍콩에 갔었는데 거기 보다 여기가 훨씬 좋은 것 같다라고 말했다";
    final String sampleTextDescrip3="[출처] 비즈엔터 : 류동우 기자 | 입력 : 2017-10-19 21:36 | \n http://enter.etoday.co.kr/view/news_view.php?varAtcId=123761#csidx2dac4ce8956fd208f5fe1984af370f1 ";
    final String sampleText4="며칠 전에도 왔었는데 또 왔네라며";
    final String sampleTextDescrip4="[출처] 뉴스1 장수민 기자 | 입력 : 2017-10-19 00:20 | \n http://news1.kr/articles/?3128014";
    int sampleIndex=1;


    int textLength;
    int editTextLength;
    char lastChar;
    int timerIndex = 0;
    ImageView imageView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    Button resetButton;
    TextView ellapse;
    char positionChar;
    int currenTextLength = 0;
    int previousTextLength = 0;
    CheckBox checkBox;

    final static int IDLE = 0;
    long mBaseTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        resetButton = (Button) findViewById(R.id.resetButton);
        leftArrow = (ImageView) findViewById(R.id.leftArrow);
        rightArrow = (ImageView) findViewById(R.id.rightArrow);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        autofitLayout = (AutofitLayout) findViewById(R.id.autoFit);
        autofitLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.performClick();
            }
        });

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( sampleIndex == 1 ) {
                    sampleIndex = 4;
                    textView3.setText(sampleText4);
                    textView4.setText(sampleTextDescrip4);
                    editText.setText("");
                    stopTimer();
                    ellapse.setText("00:00:00");
                } else if ( sampleIndex == 2 ) {
                    sampleIndex = 1;
                    textView3.setText(sampleText1);
                    textView4.setText(sampleTextDescrip1);
                    editText.setText("");
                    stopTimer();
                    ellapse.setText("00:00:00");
                } else if ( sampleIndex == 3 ) {
                    sampleIndex = 2;
                    textView3.setText(sampleText2);
                    textView4.setText(sampleTextDescrip2);
                    editText.setText("");
                    stopTimer();
                    ellapse.setText("00:00:00");
                } else if ( sampleIndex == 4 ) {
                    sampleIndex = 3;
                    textView3.setText(sampleText3);
                    textView4.setText(sampleTextDescrip3);
                    editText.setText("");
                    stopTimer();
                    ellapse.setText("00:00:00");
                }
            }
        });
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( sampleIndex == 1 ) {
                    sampleIndex = 2;
                    textView3.setText(sampleText2);
                    textView4.setText(sampleTextDescrip2);
                    editText.setText("");
                    stopTimer();
                    ellapse.setText("00:00:00");
                } else if ( sampleIndex == 2 ) {
                    sampleIndex = 3;
                    textView3.setText(sampleText3);
                    textView4.setText(sampleTextDescrip3);
                    editText.setText("");
                    stopTimer();
                    ellapse.setText("00:00:00");
                } else if ( sampleIndex == 3 ) {
                    sampleIndex = 4;
                    textView3.setText(sampleText4);
                    textView4.setText(sampleTextDescrip4);
                    editText.setText("");
                    stopTimer();
                    ellapse.setText("00:00:00");
                } else if ( sampleIndex == 4 ) {
                    sampleIndex = 1;
                    textView3.setText(sampleText1);
                    textView4.setText(sampleTextDescrip1);
                    editText.setText("");
                    stopTimer();
                    ellapse.setText("00:00:00");
                }
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                stopTimer();
                ellapse.setText("00:00:00");
            }
        });
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        ellapse = (TextView) findViewById(R.id.ellapse);
        textView3.setText(sampleText1);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView4.setText(sampleTextDescrip1);

        editText.addTextChangedListener(this);
    }

    @Override
    protected void onDestroy() {
        mTimer.removeMessages(0);
        super.onDestroy();
    }

    //스톱워치는 위해 핸들러를 만든다.

    Handler mTimer = new Handler(){





        public void handleMessage(android.os.Message msg) {

            //텍스트뷰를 수정해준다.

            ellapse.setText(getEllapse());

            //메시지를 다시 보낸다.

            mTimer.sendEmptyMessage(0);//0은 메시지를 구분하기 위한 것

        };

    };

    public String getEllapse(){

        long now = SystemClock.elapsedRealtime();

        long ell = now - mBaseTime;//현재 시간과 지난 시간을 빼서 ell값을 구하고

        //아래에서 포맷을 예쁘게 바꾼다음 리턴해준다.

        String sEll = String.format("%02d:%02d:%02d", ell / 1000 / 60, (ell/1000)%60, (ell %1000)/10);

        return sEll;

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (timerIndex == 0) {
            startTimer();
            timerIndex = 1;
        }

        currenTextLength = editText.getText().toString().length();
        fullStringOfEditText = editText.getText().toString();
        String[] parsedText = fullStringOfEditText.split("\\s+");
//        for (int i = 0; i < parsedText.length; i++) {
//            System.out.println("parsedText [" + i + "] = \"" + parsedText[i] + "\"");
//        }


        int numOfParsed = parsedText.length;
//        textView.setText(String.valueOf(numOfParsed));

//        if (numOfParsed >= 2) {
//            stringToDisplay = "";
//            for (int i=0; i < numOfParsed; i++) {
//                stringToDisplay = stringToDisplay + replaceAll(parsedText[i]) + " ";
//                System.out.println("for문 내부의 [" + i + "] stringToDisplay: "+String.valueOf(stringToDisplay));
//            }
//        } else {
//            stringToDisplay = replaceAll(fullStringOfEditText);
//        }
        if (checkBox.isChecked()) {
            stringToDisplay = replaceAll(fullStringOfEditText);
        } else {
            stringToDisplay = fullStringOfEditText;
        }
//        System.out.println("for문 끝나고 난 뒤의 stringToDisplayFront: "+String.valueOf(stringToDisplayFront));
//        System.out.println("for문 끝나고 난 뒤의 stringToDisplay: "+String.valueOf(stringToDisplay));


        textView2.setText(stringToDisplay);

        if (stringToDisplay.equals(textView3.getText().toString())) {
            stopTimer();
            timerIndex = 0;
        }
    }


    public String replaceAll(String textTemp2) {
        textTemp2 = textTemp2.replace('엇','었');
        textTemp2 = textTemp2.replace('잣','잤');
        textTemp2 = textTemp2.replace('잇','있');
        textTemp2 = textTemp2.replace('엿','였');
        textTemp2 = textTemp2.replace('왓','왔');
        textTemp2 = textTemp2.replace('갓','갔');
        textTemp2 = textTemp2.replace('줫','줬');
        textTemp2 = textTemp2.replace('낫','났');
        textTemp2 = textTemp2.replace('혓','혔');
        textTemp2 = textTemp2.replace('햇','했');
        textTemp2 = textTemp2.replace('랏','랐');
        textTemp2 = textTemp2.replace('겟','겠');
        textTemp2 = textTemp2.replace('넷','넸');
        textTemp2 = textTemp2.replace('졋','졌');
        textTemp2 = textTemp2.replace('앗','았');
        textTemp2 = textTemp2.replace('웟','웠');
        textTemp2 = textTemp2.replace('됏','됐');
        textTemp2 = textTemp2.replace('랫','랬');
        textTemp2 = textTemp2.replace('렷','렸');
        textTemp2 = textTemp2.replace('깻','깼');
        textTemp2 = textTemp2.replace("오랬","오랫");
        textTemp2 = textTemp2.replace("있몸","잇몸");
        textTemp2 = textTemp2.replace("무었","무엇");
        textTemp2 = textTemp2.replace("했반","햇반");
        textTemp2 = textTemp2.replace("갔김치","갓김치");
        textTemp2 = textTemp2.replace("했살","햇살");
        return textTemp2;
    }

    public void startTimer() {
        mBaseTime = SystemClock.elapsedRealtime();
        //핸드러로 메시지를 보낸다
        mTimer.sendEmptyMessage(0);
    }

    public void stopTimer() {
        mTimer.removeMessages(0);
//        mPauseTime = SystemClock.elapsedRealtime();
        timerIndex = 0;
    }
}
