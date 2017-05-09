package ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;
import com.shockwave.pdfium.PdfDocument;
import com.tylerbrady34gmail.leadprepper.R;

import java.util.List;

/**
 * Created by tyler on 5/4/2017.
 * Shows our PDF, uses the 'com.github.barteksc:android-pdf-viewer:2.0.3' dependency
 * which i found here: https://deepshikhapuri.wordpress.com/2017/02/15/display-pdf-file-inside-my-android-application/
 * Also referenced here: https://www.quora.com/How-do-you-display-PDF-files-inside-an-Android-application-instead-of-loading-in-WebView-by-appending-PDF-URL-with-a-Google-DOCs-Url-and-launching-a-third-party-PDF-rendering-application-installed-in-the-device-Are-there-any-good-free-PDF-rendering-libraries-available
 * It works like a charm!
 */

public class MyPdfViewActivity extends AppCompatActivity implements OnPageChangeListener,OnLoadCompleteListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    PDFView pdfView;
    Integer pageNumber = 1;
    String pdfFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);
        pdfView = (PDFView)findViewById(R.id.pdfView);
        pdfFileName = "LEAD2017_v2.pdf";//the name of our PDF we want to view
        displayFromAsset();
    }
    /**Displays the PDF from our assests*/
    private void displayFromAsset() {

        pdfView.fromAsset(pdfFileName)
                .defaultPage(pageNumber)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }


    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }
    /**Prints our the pdf*/
    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean bool = super.onCreateOptionsMenu(menu);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        getMenuInflater().inflate(R.menu.mymenu,menu);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        //IconDrawable draw = new IconDrawable(this, Iconify.IconValue.fa_ellipsis_h).colorRes(R.color.white).sizeDp(40);
        //menu.getItem(0).setIcon(draw);//sets filter item to have the right icon
        return bool;
    }
}
