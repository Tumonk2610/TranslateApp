package com.cardiomood.hoanglong.fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.cardiomood.hoanglong.getSuggest.JSONData;
import com.cardiomood.hoanglong.getSuggest.WritingGuide;
import com.cardiomood.hoanglong.R;
import com.cardiomood.hoanglong.adapter.SuggestAdapter;
import com.cardiomood.hoanglong.api.Api;
import com.cardiomood.hoanglong.getSuggest.Request;
import com.cardiomood.hoanglong.model.Suggest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedString;

import static android.app.Activity.RESULT_OK;

public class InputFragment extends Fragment {

    private static final String TAG = "hehe" ;
    private Spinner spnCategory;
    private EditText InputEditText;
    private LinearLayout lnHandingInput;

    DrawingView dv ;
    private Paint mPaint;
    private RelativeLayout rlDraw;
    private View viewDraw;
    ArrayList<Integer> arPointDx = new ArrayList<>();
    ArrayList<Integer> arPointDy = new ArrayList<>();
    ArrayList<Integer> arPointT = new ArrayList<>();

    int milis = 0;

    ImageView ivDone;
    List<Suggest> suggestList = new ArrayList<>();
    SuggestAdapter suggestAdapter;
    RecyclerView rvSuggest;

    Button btnSpace,btnIcon,btnDel;

    String srcText = "";

    public static String stringToPassBack;

    public InputFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        setupUI(view);

        if(TranslationFragment.isCheckPaint){
            lnHandingInput.setVisibility(View.VISIBLE);
            spnCategory.setVisibility(View.GONE);
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        return view;
    }

    private void setupUI(View view) {
        spnCategory = (Spinner) view.findViewById(R.id.sp_input);
        InputEditText = (EditText) view.findViewById(R.id.input_et);
        lnHandingInput = (LinearLayout) view.findViewById(R.id.ln_handing);
        lnHandingInput.setVisibility(View.GONE);
        ivDone = (ImageView) view.findViewById(R.id.done);
        rvSuggest = (RecyclerView) view.findViewById(R.id.rv_suggest);
        btnSpace = (Button) view.findViewById(R.id.btn_space);
        btnDel = (Button) view.findViewById(R.id.btn_del);
        btnIcon = (Button) view.findViewById(R.id.btn_icon);

        List<String> list = new ArrayList<>();
        list.add("Bàn phím");
        list.add("Chữ viết tay");

        rlDraw = (RelativeLayout) view.findViewById(R.id.rl_draw);
        dv = new DrawingView(getContext());
        rlDraw.addView(dv);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);

        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spnCategory.setAdapter(adapter);
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), spnCategory.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                if(spnCategory.getSelectedItem().toString().equals("Bàn phím")){
                    InputEditText.requestFocus();
                    InputEditText.setFocusableInTouchMode(true);

                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(InputEditText, InputMethodManager.SHOW_FORCED);
                }else if(spnCategory.getSelectedItem().toString().equals("Chữ viết tay")){
                    Log.d("aa", "onItemSelected:  co vao" );
                    lnHandingInput.setVisibility(View.VISIBLE);
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ivDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                done();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!srcText.equals("")) {
                    srcText = srcText.substring(0, srcText.length() - 1);
                    InputEditText.setText(srcText);
                }
            }
        });

        btnSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                srcText += " ";
                InputEditText.setText(srcText);
            }
        });

        btnIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                srcText += ":-)";
                InputEditText.setText(srcText);
            }
        });
    }

    private void done() {
        stringToPassBack = InputEditText.getText().toString();

        // put the String to pass back into an Intent and close this activity

        Intent intent = new Intent();
        intent.putExtra("keyName", stringToPassBack);
        getActivity().setResult(RESULT_OK, intent);
        getActivity().onBackPressed();

        Log.d(TAG, "done: " + stringToPassBack);
    }

    public class DrawingView extends View {

        public int width;
        public  int height;
        private Bitmap mBitmap;
        private Canvas mCanvas;
        private Path mPath;
        private Paint   mBitmapPaint;
        Context context;
        private Paint circlePaint;
        private Path circlePath;

        public DrawingView(Context c) {
            super(c);
            context=c;
            mPath = new Path();
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);
            circlePaint = new Paint();
            circlePath = new Path();
            circlePaint.setAntiAlias(true);
            circlePaint.setColor(Color.BLUE);
            circlePaint.setStyle(Paint.Style.STROKE);
            circlePaint.setStrokeJoin(Paint.Join.MITER);
            circlePaint.setStrokeWidth(4f);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);

            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawBitmap( mBitmap, 0, 0, mBitmapPaint);
            canvas.drawPath( mPath,  mPaint);
            canvas.drawPath( circlePath,  circlePaint);
        }

        private float mX, mY;
        private static final float TOUCH_TOLERANCE = 4;

        private void touch_start(float x, float y) {
            mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
            milis = 0;
        }

        private void touch_move(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
                mX = x;
                mY = y;

                circlePath.reset();
                circlePath.addCircle(mX, mY, 30, Path.Direction.CW);
            }

            milis = (int) (System.currentTimeMillis() % 1000);

            Log.d(TAG, "touch_move: " + milis );


            Point point = new Point();
            point.x = (int) mX;
            point.y = (int) mY;
            point.t = milis;

            arPointDx.add(point.x);
            arPointDy.add(point.y);
            arPointT.add(point.t);

        }

        private void touch_up() {
            mPath.lineTo(mX, mY);
            circlePath.reset();
            // commit the path to our offscreen
            mCanvas.drawPath(mPath,  mPaint);
            // kill this so we don't double draw
            mPath.reset();
            for(int i=0;i<arPointDy.size();i++){
                Log.d(TAG, "dx: " + arPointDx);
                Log.d(TAG, "dy: " + arPointDy);
                Log.d(TAG, "dt: " + arPointT);
            }
            milis = 0;


            WritingGuide createWritingGuide = new WritingGuide(1280,720);

            ArrayList a = new ArrayList();
            ArrayList b = new ArrayList();

            b.add(0,arPointDx);
            b.add(1,arPointDy);
            b.add(2,arPointT);

            a.add(b);

            Log.d(TAG, "touch_up: " + a.toString());

            Request request = new Request(createWritingGuide,a,"en");

            List<Request> requests = new ArrayList<>();
            requests.add(request);


            JSONData jsonData = new JSONData("Chrome/19.0.1084.46 Safari/536.5","enable_pre_space",requests);

            Log.d(TAG, "" + jsonData.toString());

            test();

            Log.d(TAG, "ink: " + a);

        }

        private void test(){



            TypedString typedString = new TypedString("{\n" +
                    "   \"device\":\"Chrome/19.0.1084.46 Safari/536.5\",\n" +
                    "   \"options\":\"enable_pre_space\",\n" +
                    "   \"requests\":[{\"writing_guide\":{\n" +
                    "     \"writing_area_width\":1920,\n" +
                    "     \"writing_area_height\":617},\n" +
                    "    \"ink\":[["+ arPointDx +",\n" +
                    arPointDy+",\n" +
                    arPointT +
                    "]],\n" +
                    "     \"language\":\"en\"}]}");
            Api api = new Api();
            api.serviceViewOrClick2().addAction(typedString, new Callback<Response>() {
                @Override
                public void success(Response strings, Response response) {

                    String bodyString = new String(((TypedByteArray) strings.getBody()).getBytes());
                    Log.e("thanhcong", bodyString);
                    try {
                        JSONArray jsonArray = new JSONArray(bodyString);
                        JSONArray jsonArray1  = jsonArray.getJSONArray(1);
                        JSONArray jsonArray2 =  jsonArray1.getJSONArray(0);
                        JSONArray jsonArray3 = jsonArray2.getJSONArray(1);
                        for (int i = 0; i < jsonArray3.length() ; i++) {
                            Log.e("aaaa", jsonArray3.getString(i));
                            Suggest suggest = new Suggest(jsonArray3.getString(i));
                            suggestList.add(suggest);
                            Log.d(TAG, "success: " + suggestList.get(i).toString());
                        }
                        suggestAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("thanhcong", error.getMessage()+"");
                }
            });

            suggestAdapter = new SuggestAdapter(suggestList,getContext());
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            rvSuggest.setLayoutManager(layoutManager);
            rvSuggest.hasFixedSize();
            rvSuggest.setAdapter(suggestAdapter);

            suggestAdapter.setOnItemClick(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Suggest suggestModel = (Suggest) view.getTag();
                    Log.d(TAG, "onClickSuggest: " + view.getTag());
                    srcText += suggestModel.getText();
                    InputEditText.setText(srcText);
                }
            });

            suggestList.removeAll(suggestList);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touch_start(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    touch_move(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    touch_up();
                    invalidate();
                    break;
            }
            return true;
        }
    }

    static class Point{
        int x;
        int y;
        int t;
    }

}
