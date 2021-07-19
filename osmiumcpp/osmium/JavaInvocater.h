#pragma once
#include <jni.h>
#include <string>

class JavaInvocater
{
public:
	JavaInvocater(std::string library_path);
	void CallJar(std::string structure, int playerX, int playerZ);
	void UpdateGameDir(std::string game_dir);
};

