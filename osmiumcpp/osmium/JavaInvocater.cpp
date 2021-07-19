#include "JavaInvocater.h"  
#include <iostream>

JNIEnv* env;
JavaVM* jvm;
jclass cls;

using namespace std;

JavaInvocater::JavaInvocater(std::string library_path) {
    string optionString = "-Djava.class.path="+library_path;

    JavaVMInitArgs vm_args;
    JavaVMOption* options = new JavaVMOption[1];
    options[0].optionString = const_cast<char*>(optionString.c_str());
    vm_args.version = JNI_VERSION_1_8;
    vm_args.nOptions = 1;
    vm_args.options = options;
    vm_args.ignoreUnrecognized = false;
    try {
        JNI_CreateJavaVM(&jvm, (void**)&env, &vm_args);
    }
    catch (exception e) {
        std::cout << "[error]: Unnable to find Osmium.jar. Make sure the library is located at"
            + library_path;
        system("pause");
        exit(0);
    }
    delete options;
    cls = env->FindClass("osmium/Osmium");
    if (cls == 0) {
        std::cout << "[error]: Error while trying to find main class. ";
        system("pause");
        exit(0);
    }
    
    jmethodID instantiate = env->GetStaticMethodID(cls, "instantiate", "()V");
    env->CallStaticVoidMethod(cls, instantiate);
}

void JavaInvocater::CallJar(std::string structure, int playerX, int playerZ) {
    jstring structureJstring = env->NewStringUTF(const_cast<char*>(structure.c_str()));

    jmethodID getLocation = env->GetStaticMethodID(cls, "getLocation", "(Ljava/lang/String;II)V");
    env->CallStaticVoidMethod(cls, getLocation, structureJstring, playerX, playerZ);
}

void JavaInvocater::UpdateGameDir(std::string game_dir) {
    jstring dir = env->NewStringUTF(const_cast<char*>(game_dir.c_str()));

    jmethodID setSaveDir = env->GetStaticMethodID(cls, "setSaveDir", "(Ljava/lang/String)V");
    env->CallStaticVoidMethod(cls, setSaveDir, dir);
}