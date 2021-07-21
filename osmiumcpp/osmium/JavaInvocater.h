#pragma once
#include <jni.h>
#include <string>
#include <vector>

class JavaInvocater
{
public:
	JavaInvocater(std::string library_path);
	std::vector<int> CallJar(std::string structure, int playerX, int playerZ);
	void UpdateGameDir(std::string game_dir);
};

