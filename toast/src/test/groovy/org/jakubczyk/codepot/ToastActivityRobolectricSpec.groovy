package org.jakubczyk.codepot

import org.robolectric.Robolectric
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowToast
import pl.polidea.robospock.RoboSpecification

@Config(manifest = "./src/main/AndroidManifest.xml")
class ToastActivityRobolectricSpec extends RoboSpecification {

    def "should assign listener to the label"() {
        given: "create activity controller"
        def controller = Robolectric.buildActivity(ToastActivity)
        and: "get the activity from controller"
        def activity = controller.get();
        when: "make controller call onCreate"
        controller.create()
        then: "check if listener is set"
        def shadow =  Robolectric.shadowOf(activity.labelTv);
        shadow.onClickListener
    }

    def "should show a toast on click event"() {
        given: "create activity controller"
        def controller = Robolectric.buildActivity(ToastActivity)
        and: "get the activity from controller"
        def activity = controller.get();
        and: "make controller call onCreate"
        controller.create()
        when: "call action, click on the label"
        activity.labelTv.performClick();
        then: "check if correct text in the Toast is set"
        ShadowToast.latestToast =~ "Clicked!"
        //1* activity.toaster.show("Clicked!")
    }
}