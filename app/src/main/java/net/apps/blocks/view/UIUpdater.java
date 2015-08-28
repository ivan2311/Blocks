package net.apps.blocks.view;

import android.os.Handler;
import android.os.Looper;

/**
 * A class used to perform periodical updates,
 * specified inside a runnable object. An update interval
 * may be specified (otherwise, the class will perform the
 * update every 2 seconds).
 *
 * @author Korisnik
 */
public class UIUpdater {
        // Create a Handler that uses the Main Looper to run in
        private Handler mHandler = new Handler(Looper.getMainLooper());

        private Runnable mStatusChecker;
        private static final int UPDATE_INTERVAL = 2000;

        /**
         * Creates an UIUpdater object, that can be used to
         * perform UIUpdates on a specified time interval.
         * 
         * @param uiUpdater A runnable containing the update routine.
         * @param interval  The interval over which the routine
         *                  should run (milliseconds).
         */
        public UIUpdater(final Runnable uiUpdater, final int interval) {
            mStatusChecker = new Runnable() {
                @Override
                public void run() {
                    // Run the passed runnable
                    uiUpdater.run();
                    // Re-run it after the update interval
                    mHandler.postDelayed(this, interval);
                }
            };
        }

        /**
         * The same as the default constructor, but specifying the
         * intended update interval.
         * 
         * @param uiUpdater A runnable containing the update routine.
         *
         */
        public UIUpdater(Runnable uiUpdater){
            this(uiUpdater, UPDATE_INTERVAL);
        }

        /**
         * Starts the periodical update routine (mStatusChecker 
         * adds the callback to the handler).
         */
        public synchronized void startUpdates(){
            mStatusChecker.run();
        }

        /**
         * Stops the periodical update routine from running,
         * by removing the callback.
         */
        public synchronized void stopUpdates(){
            mHandler.removeCallbacks(mStatusChecker);
        }
}