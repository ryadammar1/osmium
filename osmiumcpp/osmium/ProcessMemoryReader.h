#ifndef PROCESS_MEMORY_READER_H
#define PROCESS_MEMORY_READER_H

#include <Windows.h>
#include <TlHelp32.h>
#include <string>
#include <iostream>
#include <tchar.h>
#include <vector>
    
    //Structs

    struct Coordinates {
        float x, y, z;
    };

    class ProcessMemoryReader {
    public:
        //Constants

        const std::wstring DEFAULT_VERSION_W = L"1.16.5";
        const std::string DEFAULT_VERSION = "1.16.5";

        //Constructors

        ProcessMemoryReader();
        ProcessMemoryReader(std::string, std::wstring, boolean);

        //Functions

        int FetchCoordinates();
        void PrintCooridnates();
        Coordinates GetCoordinates();

        //Variables

        std::wstring version_w;
        std::string version;

        boolean optifine;

        Coordinates coordinates;
    };

#endif