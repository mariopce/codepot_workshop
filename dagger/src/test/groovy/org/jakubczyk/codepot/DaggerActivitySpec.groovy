package org.jakubczyk.codepot

import android.content.Context
import android.widget.TextView
import org.jakubczyk.codepot.inject.DaggerComponent
import org.jakubczyk.codepot.inject.DaggerInjector
import org.jakubczyk.codepot.inject.DaggerModule
import org.w3c.dom.Text
import spock.lang.Specification

class DaggerActivitySpec extends Specification {

    // use default Module
    def "should show first user"() {
        given: "create an activity"
        def activity = new DaggerActivity();
        and: "set a new user"
        activity.user = new User();
        and: "mock the widget"
        activity.titleTv = Mock(TextView)
        when: "call onResume"
        activity.onResume();
        then: "check if correct text is set"
        1*activity.titleTv.setText(_)
    }

    def "should show third user"() {
        given: "prepare Dagger Injector"
        DaggerInjector.start(new DaggerModule(Mock(Context)) {
            @Override
            public User provideUser(Context context) {
                return new User()
            }
        });
        and: "create an activity"
        def activity = new DaggerActivity()
        and: "mock the widget"
        activity.labelTv = Mock(TextView)
        when: "call action, update Users"
        activity.updateUsers();
        then: "check if first no 0 is set"
        1 * activity.titleTv.setText("name='Adam no. 1 fromPackage=null")
        1 * activity.titleTv.setText("name='Adam no. 2 fromPackage=null")
        1 * activity.titleTv.setText("name='Adam no. 2 fromPackage=null")
    }

    // replace "Component"
    def "should show first user with component"() {
        given: "Override DaggerInjector component with a Mock"
        DaggerInjector.component = Mock(DaggerComponent){
            inject(_)  >>  { arguments ->
                arguments[0].user = new User();
            }
        }
        and: "create an activity"
        def activity = new DaggerActivity();
        and: "call OnCreate"
        activity.onCreate(null)
        and: "mock the widget"
        activity.titleTv = Mock(TextView)
        when: "call onResume"
        activity.onResume()
        then: "check if correct text is set"
        1 * activity.titleTv.setText("name='Adam no. 1 fromPackage=null")


    }

    def "should show thrid user with component"() {
        given: "Override DaggerInjector component with a Mock"

        and: "create an activity"

        and: "mock the wigdet"

        when: "call action, update Users()"

        then: "check if 3 times the label was set"
    }
}