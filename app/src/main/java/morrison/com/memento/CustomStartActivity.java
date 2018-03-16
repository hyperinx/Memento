package morrison.com.memento;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Michael on 13.11.2017.
 */

public class CustomStartActivity {

    public static void executeActivity(Activity initiatorActivity,
                                       Class<? extends Activity> destinationClass,
                                       String key, String data) {
        Intent i = new Intent(initiatorActivity, destinationClass);

        if (!key.equals("") && !data.equals("")) {
            i.putExtra(key, data);
        }

        initiatorActivity.startActivity(i);
        initiatorActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public static void finishActivity(Activity initiatorActivity) {

        initiatorActivity.finish();
        initiatorActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_righ);

    }
}

