一、buildSrc project
在项目工程的根目录下创建buildSrc目录，这个名字不能错，必须是buildSrc。
创建完之后，rebuild一下工程，会在buildSrc下生成一些目录。


二、Gradle Versions Plugin 插件去检查依赖库的最新版本
那么如何使用呢？只需要三步


1.将 项目根目录 checkVersions.gradle 文件拷贝到你的项目根目录下面


2.在项目的根目录 build.gradle 文件夹内添加以下代码
apply from: './checkVersions.gradle'
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath "com.github.ben-manes:gradle-versions-plugin:0.28.0"
    }
}
复制代码


3.添加完成之后，在根目录下执行以下命令。
./gradlew dependencyUpdates
复制代码
会在当前目录下生成 build/dependencyUpdates/report.txt 文件。


会列出所有需要更新的依赖库的最新版本，并且 Gradle Versions Plugin 比 AndroidStudio 所支持的更加全面：

支持手动方式管理依赖库最新版本检查
支持 ext 的方式管理依赖库最新版本检查
支持 buildSrc 方式管理依赖库最新版本检查
支持 gradle-wrapper 最新版本检查
支持多模块的依赖库最新版本检查

