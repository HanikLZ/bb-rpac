import org.mdvsc.rapc.RapcExtension
import org.mdvsc.rapc.RapcPlugin

plugins {
    kotlin("jvm")
}
apply<RapcPlugin>()

configure<RapcExtension> {
    app {
        name = "SampleAPpp"
        version = "1.0.0" // Default project version
        vendor = "MDVSC"
        description = "Rapc build Sample"
        entry {
            // arguments = "org.mdvsc.rapc.sample.SampleMidlet" // 额外参数，若为midlet编译，此为入口类
            icon {
                normal = "icon.png"
            }
        }
    }
}