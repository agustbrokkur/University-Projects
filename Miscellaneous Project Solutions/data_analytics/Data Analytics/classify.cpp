//
// Problem Solving Fall 2015  Data Analytics - Classification
//
#include <iostream>
#include <fstream>
#include <sstream>
#include <iomanip>
#include <vector>

using namespace std;

const string FILENAME_WEATHER = "weather.arff";
const string FILENAME_CONTACT_LENSES = "contact-lenses.arff";
const string FILENAME_MUSHROOMS  = "mushrooms.arff";
const string FILENAME_MUSHROOMS_TEST  = "mushrooms_test.arff";  // Do not use this file at first.


// ==============  You need not change anything here above ==================

const string FILENAME = FILENAME_MUSHROOMS; // <== Change to one of the above files, depending on dataset to use.

//
//  NOTE: Open the arff files in a text editor to learn more about the fields/attributes
//        and what values they can take.
//

string classify_weather( const vector<string>& fields ) {

    //
    // ... insert your rules here (replacing the example field below) ...
    //
    return ((fields[0] == "overcast") || (fields[0] == "sunny" && fields[2] == "normal") || (fields[0] == "rainy" && fields[3] == "FALSE")) ? "yes" : "no";
      // returns the class it things the records belongs to  ("no" or "yes").
}


string classify_contactlens( const vector<string>& fields ) {

    //
    // ... insert your rules here ...
    //
    return (fields[2] == "no" && fields[3] != "reduced" && (fields[0] != "presbyopic" || fields[1] != "myope")) ? "soft" : (fields[2] == "yes" && fields[3] != "reduced" && (fields[0] == "young" || fields[1] == "myope")) ? "hard" : "none";
    //returns the class it things the records belongs to  ("none", "soft" or "hard").
}


string classify_mushroom( const vector<string>& fields ) {

    //
    // ... insert your rules here ...
    //

    return ( (((fields[4] == "n" || fields[4] == "l" || fields[4] == "a")  && (fields[18] != "w" || (fields[0] == "k" || fields[0] == "x" || fields[0] == "f" || fields[0] == "b") )) && fields[17] != "l"  && fields[18] != "h")) ? "e" : "p";
    //returns the class it things the records belongs to  ("p" or "e", i.e. poisonous or edible).
}


// ==============  You need not change anything here below ==================

vector<string> split( string line, char delim ) {
    vector<string> fields;
    stringstream ss(line);
    string field;
    while ( getline(ss,field, delim)) {
        fields.push_back(field);
    }
    return fields;
}


int main() {
    cout << "Welcome to the world of analytics!\n" << endl;

    ifstream file_arff;
    file_arff.open( FILENAME.c_str() );

    bool process_data = false;
    string line;
    int classified = 0;
    int correctly_classified = 0;

    while (getline(file_arff, line)) {

        if ( line.empty() || line.at(0) == '%' ) {
            // Skip line, this is an empty line or a comment.
        }
        else if ( line == "@data" || line == "@DATA" ) {
            process_data = true;
        }
        else if ( process_data ) {
            vector<string> fields = split(line, ',');
            string correct_class = fields.back();
            fields.pop_back();

            string outcome;
            if ( FILENAME == FILENAME_WEATHER ) {
                outcome = classify_weather(fields);
            }
            else if ( FILENAME == FILENAME_CONTACT_LENSES ) {
                outcome = classify_contactlens(fields);
            }
            else if ( FILENAME == FILENAME_MUSHROOMS ) {
                outcome = classify_mushroom(fields);
            }
            else if ( FILENAME == FILENAME_MUSHROOMS_TEST ) {
                outcome = classify_mushroom(fields);
            }
            else {
                cout << "=======> Unknown filename: " << FILENAME;
            }
            bool correct = (outcome == correct_class);
            if ( correct ) {
                correctly_classified++;
            }
            classified++;
            cout << correct << ' ' << line << endl;
        }
    }
    file_arff.close();

    cout << "\n==========================================================================" << endl;
    cout << classified << " instances classified, thereof " << correctly_classified << " correctly";
    if ( classified > 0 ) {
        cout << " (accuracy=" << std::setprecision(4) << 100.0*correctly_classified/classified << "%)";
    }
    cout << "\n==========================================================================" << endl;

    return 0;
}
// Dataset      Accuracy    Lines of Code
// weather          100%                1
// contact-lenses   100%                1
// mushrooms      98.45%                1

