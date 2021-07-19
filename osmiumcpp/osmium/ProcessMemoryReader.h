#ifndef PROCESS_MEMORY_READER_H
#define PROCESS_MEMORY_READER_H

#include <Windows.h>
#include <TlHelp32.h>
#include <string>
#include <iostream>
#include <tchar.h>
#include <vector>

using namespace std;

    struct Coordinates {
        float x, y, z;
    };

    class ProcessMemoryReader {
    public:
        ProcessMemoryReader();

        int FetchCoordinates();
        void PrintCooridnates();
        Coordinates GetCoordinates();

        Coordinates coordinates;

    private:
        HWND GetHandle(std::string);
        string WcharString(wchar_t* str);
        uintptr_t GetModuleBaseAddress(const wchar_t* lpsz_module_name, DWORD pID);
    };

#endif