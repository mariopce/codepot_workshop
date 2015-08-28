package org.jakubczyk.codepot

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import org.robolectric.Robolectric
import org.robolectric.annotation.Config
import pl.polidea.robospock.RoboSpecification

@Config(manifest = "./src/main/AndroidManifest.xml")
class MainActivitySpecRobolectric extends RoboSpecification {

    def "should build the activity"() {
        given: "create activity controller"
        def controller = Robolectric.buildActivity(MainActivity)
        and: "get the activity from controller"
        def activity = controller.get();
        when: "make controller call onCreate"
        controller.create();
        then: "check if widget is not empty"
        activity.titleTv
    }

    def "should show low value"(){
        given: "create activity controller"
        def controller = Robolectric.buildActivity(MainActivity)
        and: "get the activity from controller"
        def activity = controller.get();
        and: "set conditional value to 2"
        activity.conditionValue = 2
        when: "make controller call onCreate and onResume"
        controller.create().resume();
        then: "check if correct text is set"
        activity.titleTv.text =~ "Low"
    }

    def "should show high value"(){
        given: "create activity controller"
        def controller = Robolectric.buildActivity(MainActivity)
        and: "get the activity from controller"
        def activity = controller.get();
        and: "set conditional value to 4"
        activity.conditionValue = 4
        when: "make controller call onCreate and onResume"
        controller.create().resume();
        then: "check if correct text is set"
        activity.titleTv.text =~ "High"
    }

    // The Intent

    def "should show that bundle is empty"() {
        given: "create activity controller with intent and call create"
        Intent i = new Intent();
        Bundle b = new Bundle();
        b.putString("key1", "val1");
        b.putString("key2", "val2");
        i.putExtras(b);
        def controller = Robolectric.buildActivity(MainActivity).withIntent(i).create();
        and: "get the activity from controller"
        def activity = controller.get();
        when: "call action"
        activity.processExtras();
        then: "check if correct text is set"
        activity.labelTv.text =~ "with 2"
    }

    def "should show bundle size"(){
        given: "fill bundle with elements"

        and: "pass it to the intent"

        and: "create activity controller with intent and call create"

        and: "get the activity from controller"

        when: "call action"

        then: "check if correct text is set"
    }

}