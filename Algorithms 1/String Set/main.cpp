#include <iostream>
#include <fstream>
#include <cstdlib>
#include <cmath>
#include <vector>
#include "stringset.h"


using namespace std;


// The following functions are used
// within the main() function.
// You should implement those functions
// as well as the StringSet class.
//void readFileKeywords(StringSet& setDocument, const char filename[]);
//double similarity(const StringSet& set1, const StringSet& set2);


void readFileKeywords(StringSet& setDocument, const char filename[])
{
    string keyword;
    ifstream file(filename);
    file.open(filename);
    if (file.is_open())
    {
        while (file >> keyword)
        {
            setDocument._Strengir.push_back(keyword);
        }
    }
    else
    {
        cout << "unable to open file";
    }
}

double similarity(const StringSet& set1, const StringSet& set2)
{
//    double result;
//    result = (set2 & set1) / (sqrt(set2) * sqrt(set1));
    return 0; //result;
}


int main()
{
    StringSet doc1;
    StringSet doc2;
    StringSet theUnion;
    StringSet query;


    readFileKeywords(doc1, "doc1.txt");
    readFileKeywords(doc2, "doc2.txt");


    cout << "Doc1: " << doc1;
    cout << endl;
    cout << "Doc2: " << doc2;
    cout << endl;/*
    theUnion = doc1 + doc2;
    cout << "Union: " << theUnion;
    cout << endl;


    //readFileKeywords(query, "query.txt");
    cout << "Query: " << query;
    cout << endl;


    int count = 0;
    for (unsigned int i = 0; i < query.size(); i++)
    {
         if (theUnion.find(query.at(i)))
        {
            count++;
        }
    }
    //cout << "Query size: " << query.size();
    cout << endl;
    cout << "Found in union: " << count;
    cout << endl;


    // Calculate similarity to each document
    //double sim1 = similarity(doc1, query);
    cout << "Similarity to doc1: " << sim1;
    cout << endl;


    //double sim2 = similarity(doc2, query);
    cout << "Similarity to doc2: " << sim2;
    cout << endl;*/


    return 0;
}
