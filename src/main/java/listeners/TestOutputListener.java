package listeners;

import org.testng.*;


public class TestOutputListener implements ISuiteListener {


    @Override
    public void onStart(ISuite iSuite) {
        System.out.println(">>>>>>>>> SUITE LISTENER STARTED <<<<<<<<<<<<<");
    }

    @Override
    public void onFinish(ISuite iSuite) {
        System.out.println(">>>>>>>>> SUITE LISTENER FINISHED <<<<<<<<<<<<<");

    }
}
