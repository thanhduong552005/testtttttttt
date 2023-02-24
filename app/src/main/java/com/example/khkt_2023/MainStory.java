package com.example.khkt_2023;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.khkt_2023.models.Story;
import com.example.khkt_2023.models.StoryAdapter;
import com.example.khkt_2023.models.Suggestion;
import com.example.khkt_2023.models.SuggestionAdapter;
import com.example.khkt_2023.ui.main.MainFragment;
import com.example.khkt_2023.ui.main.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainStory extends AppCompatActivity implements MainStory1 {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.story_main);
//    }
    private MainViewModel mViewModel;
    public String endpoint = "https://e946-2405-4803-c7a8-2ac0-ffff-ffff-ffff-ffe8.ap.ngrok.io/api/v1/upload";
    public MainStory(){}
    Button btnStory;

//    public static Fragment newInstance() {
//        return new Fragment();
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_main);
        Bundle extras = getIntent().getExtras();

        btnStory =(Button) findViewById(R.id.textButtonTT);
        btnStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainStory.this, MainFragment.class);
                startActivity(intent);
            }
        });

        //myOnClickListener = new MyOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.story_card_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("hihi", "hihihi");
        adapter = new StoryAdapter(data);

    }
    public static RecyclerView recyclerView;
    public static View.OnClickListener myOnClickListener;
    public static StoryAdapter adapter;
    public static Story[] data = new Story[]{
            new Story("Story 1", "Title", "Click me daddy"),
            new Story("Story 1", "Title", "Click me daddy"),
            new Story("Story 1", "Title", "Click me daddy"),
            new Story("Story 1", "Title", "Click me daddy"),
            new Story("Story 1", "Title", "Click me daddy"),
            new Story("Story 1", "Title", "Click me daddy"),
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
    public ProgressBar pb;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        SuggestionAdapter adapter = new SuggestionAdapter(new Suggestion[]{new Suggestion("Bài 1: HIỆN THỰC LỊCH SỬ VÀ NHẬN THỨC LỊCH SỬ", "Lịch sử là một dòng chảy liên tục theo thời gian từ quá khứ đến hiện tại, diễn ra một lần và không lặp lại. Lịch sử được hậu thế nhận thức dựa vào những mảnh vỡ của sự kiện (Tức sử liệu) và bị chi phối bởi quan điểm chủ quan của con người. Vậy làm thế nào để tiếp cận lịch sử một cách khách quan, trung thực gần với sự thật nhất? Để trả lời cho câu hỏi này chúng ta cùng tìm hiểu vào bài học hôm nay", "xem thêm"),new Suggestion("Bài 1: HIỆN THỰC LỊCH SỬ VÀ NHẬN THỨC LỊCH SỬ", "Lịch sử là một dòng chảy liên tục theo thời gian từ quá khứ đến hiện tại, diễn ra một lần và không lặp lại. Lịch sử được hậu thế nhận thức dựa vào những mảnh vỡ của sự kiện (Tức sử liệu) và bị chi phối bởi quan điểm chủ quan của con người. Vậy làm thế nào để tiếp cận lịch sử một cách khách quan, trung thực gần với sự thật nhất? Để trả lời cho câu hỏi này chúng ta cùng tìm hiểu vào bài học hôm nay", "xem thêm"),new Suggestion("Bài 1: HIỆN THỰC LỊCH SỬ VÀ NHẬN THỨC LỊCH SỬ", "Lịch sử là một dòng chảy liên tục theo thời gian từ quá khứ đến hiện tại, diễn ra một lần và không lặp lại. Lịch sử được hậu thế nhận thức dựa vào những mảnh vỡ của sự kiện (Tức sử liệu) và bị chi phối bởi quan điểm chủ quan của con người. Vậy làm thế nào để tiếp cận lịch sử một cách khách quan, trung thực gần với sự thật nhất? Để trả lời cho câu hỏi này chúng ta cùng tìm hiểu vào bài học hôm nay", "xem thêm"), new Suggestion("Suggestion 1", "This is the first suggestionThis is the first suggestionThis is the first suggestionThis is the first suggestionThis is the first suggestion", "Click me"), new Suggestion("Bài 1: HIỆN THỰC LỊCH SỬ VÀ NHẬN THỨC LỊCH SỬ", "Lịch sử là một dòng chảy liên tục theo thời gian từ quá khứ đến hiện tại, diễn ra một lần và không lặp lại. Lịch sử được hậu thế nhận thức dựa vào những mảnh vỡ của sự kiện (Tức sử liệu) và bị chi phối bởi quan điểm chủ quan của con người. Vậy làm thế nào để tiếp cận lịch sử một cách khách quan, trung thực gần với sự thật nhất? Để trả lời cho câu hỏi này chúng ta cùng tìm hiểu vào bài học hôm nay", "xem thêm"), new Suggestion("Bài 1: HIỆN THỰC LỊCH SỬ VÀ NHẬN THỨC LỊCH SỬ", "Lịch sử là một dòng chảy liên tục theo thời gian từ quá khứ đến hiện tại, diễn ra một lần và không lặp lại. Lịch sử được hậu thế nhận thức dựa vào những mảnh vỡ của sự kiện (Tức sử liệu) và bị chi phối bởi quan điểm chủ quan của con người. Vậy làm thế nào để tiếp cận lịch sử một cách khách quan, trung thực gần với sự thật nhất? Để trả lời cho câu hỏi này chúng ta cùng tìm hiểu vào bài học hôm nay", "xem thêm")});
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.suggestion_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton btnCamera = (FloatingActionButton) findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture(view);
            }
        });

        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(View.GONE);
    }

    String currentPhotoPath;
    File photoFileGlobal;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = this.getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */);

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(this.getApplicationContext().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this.getApplicationContext(), "com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                photoFileGlobal = photoFile;

                pb.setVisibility(View.VISIBLE);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (photoFileGlobal != null) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        uploadPicture(photoFileGlobal);
                    }
                });
                t.start();
            } else {
                Log.d("huyhuhuhu", "dont know why");
            }
        }
    }

    public void takePicture(View view) {
        dispatchTakePictureIntent();
    }

    public void startActivityFromMainThread(String html) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Intent storyContent = new Intent(MainStory.this, StoryContent.class);
                storyContent.putExtra("IMAGE_PATH", photoFileGlobal.getAbsolutePath());
                storyContent.putExtra("HTML", (CharSequence) html);
                startActivity(storyContent);
            }
        });
    }
    private void uploadPicture(File photo) {
        Log.d("image path", photo.getAbsolutePath());
        URL url = null;
        try {
            url = new URL(endpoint);
            HttpURLConnection connection = null;
            DataOutputStream outputStream = null;
            String lineEnd = "\r\n";
            String twoHyphens = "--";
            String boundary = "*****";
            Log.d("debug 1", "fuck");
            int bytesRead, bytesAvailable, bufferSize;
            byte[] buffer;
            int maxBufferSize = 1 * 1024 * 1024;

            FileInputStream fileInputStream = null;
            fileInputStream = new FileInputStream(photo);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            Log.d("debug 2", "fuck");

            outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + photo.getAbsolutePath() + "\"" + lineEnd);
            outputStream.writeBytes(lineEnd);
            Log.d("debug 3", "fuck");

            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            Log.d("debug 4", "fuck");

            while (bytesRead > 0) {
                outputStream.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            Log.d("debug 5.-1", "fuck");
            int serverResponseCode = connection.getResponseCode();
            Log.d("debug 5.0", "fuck");
            outputStream.flush();
            outputStream.close();

            Log.d("debug 5.1", "fuck");

            String serverResponseMessage = connection.getResponseMessage();

            Log.d("response", String.valueOf(serverResponseCode));

            Log.d("debug 6", "fuck");

            fileInputStream.close();
            Log.d("debug 7", "fuck");

            switch (serverResponseCode) {
                case 200:
                    Log.d("debug 8", "fuck");

                    //all went ok - read response
                    pb.setVisibility(View.INVISIBLE);

                    JSONObject jsonObject = new JSONObject(serverResponseMessage);
                    String html = jsonObject.getString("Content");
                    startActivityFromMainThread(html);

                    Log.d("code", String.valueOf(serverResponseCode));
                    Log.d("message", serverResponseMessage);
                    break;

                default:
                    Log.d("debug 9", "fuck");

                    Log.d("status", serverResponseMessage);

                    //do something sensible
            }
        } catch (FileNotFoundException e) {
            Log.d("debug 10", "fuck");
            e.printStackTrace();
        } catch (ProtocolException e) {
            Log.d("debug 11", "fuck");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            Log.d("debug 12", "fuck");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("debug 13", "fuck");
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}