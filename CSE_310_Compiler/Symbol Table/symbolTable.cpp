#include<bits/stdc++.h>
 
using namespace std;
 
class SymbolInfo
{
    string name;
    string type;
    ///POINTER OF THIS CLASS TO RESOLVE COLLISION DURING HASHING
 
public:
    void setName(string x)            ///SETTER
    {
        name = x;
    }
    void setType(string x)
    {
        type = x;
    }
    string getName()                  ///GETTER
    {
        return name;
    }
    string getType()
    {
        return type;
    }
 
};
 
class ScopeTable
{
    ScopeTable *parentScope;
    int bucket_size,tot_child;
    int hash_index,pos_found;
    vector<vector<SymbolInfo>> bucket;
    string address = "";
public:
    ScopeTable(int x)
    {
        bucket_size = x;
        tot_child=0;
        parentScope=nullptr;
        address="1";
        bucket.resize(x);
    }
    ~ScopeTable(){
        bucket.clear();
        bucket.resize(0);
    }
    ScopeTable* getparentScope(){
        return this->parentScope;
    }
    void setparentScope(ScopeTable* sc){
        this->parentScope = sc;
    }
    int Hash_Function(string name, int x)
    {
        int sum = 0;
        for(auto &i:name)
        {
            sum+=i;
        }
        return sum%x;
    }
    bool Insert(string name, string type)
    {
        int index = Hash_Function(name,bucket_size);
        for(auto i=0; i<bucket[index].size(); ++i)
        {
            if( bucket[index][i].getName().compare(name) == 0 )                    ///CHECK IF TWO STRINGS ARE EQUAL
            {
                return false;                                                      ///UNSUCCESSFUL INSERTION
            }
        }
        SymbolInfo si;
        si.setName(name);
        si.setType(type);
        bucket[index].push_back(si);
        hash_index = index;
        return true;                                                               ///SUCCESSFUL INSERTION
    }
    void printScope()
    {
        cout<<"ScopeTable #"<<address<<"\n";
        for(int i=0; i<bucket_size; i++)
        {
            cout<<i<<"-->";
            /*for(auto it=bucket[i].begin(); it!=bucket[i].end(); it++)
            {
                cout<<" <"<<it->getName()<<" : "<<it->getType()<<">";
            }*/
            for(int j=0; j<bucket[i].size(); j++)
            {
                cout<<" <" << bucket[i][j].getName() << " : " << bucket[i][j].getType() << ">" ;
            }
            cout<<"\n";
        }
    }
 
    SymbolInfo* LookUp(string val)
    {
        int idx = Hash_Function(val,bucket_size);
        for(int i=0; i<bucket[idx].size(); i++)
        {
            if( bucket[idx][i].getName().compare(val) == 0 )
            {
                pos_found = i;
                cout<<"Found in ScopeTable# "<<address<<" "<<" at position "<<idx<<", "<<pos_found<<endl;
                return &bucket[idx][i];
            }
        }
 
        return NULL;
    }
 
    bool deleteSymbol(string sign)
    {
        int idx = Hash_Function(sign,bucket_size);
        int counter = 0;
        for(auto it=bucket[idx].begin() ; it!=bucket[idx].end() ; it++)
        {
            if(it->getName().compare(sign) == 0)
            {
                pos_found=counter;
                bucket[idx].erase(it);
                cout<<"Deleted Entry "<<idx<<", "<<counter<<" from current ScopeTable\n";
                return true;
            }
            counter++;
        }
        return false;
    }
 
 
    string getScopeAddress()
    {
        string str = address;
        return address;
    }
    void setScopeAddress(string parent_address,int x)   ///x / tot_child
    {
        address = parent_address + "." + to_string(x);
    }
    void incChild()
    {
        tot_child++;
    }
    int getChild()
    {
        return tot_child;
    }
    string getHashIndex()
    {
        int len = bucket[hash_index].size() - 1;
        string str = to_string(hash_index)+", "+to_string(len);
        return str;
    }
 
};
 
 
 
class SymbolTable
{
    ScopeTable *currentScope=nullptr;
 
    int bucketSize, hash_index;
    vector<ScopeTable> scopes;
public:
    SymbolTable(int x)
    {
        bucketSize = x;
        currentScope = new ScopeTable(x);
    }
 
    ~SymbolTable(){
        delete currentScope;
    }
    void printCurrentScope()
    {
        currentScope->printScope();
    }
 
    void printAllScope()
    {
    /*
        currentScope->printScope();
        int range = scopes.size();
        ScopeTable tempScope(bucketSize);
        for(int i=range-1; i>=0; i--)
        {
            tempScope = scopes[i];
            tempScope.printScope();
        }
    */
        ScopeTable* tempScope = currentScope;
        while(tempScope != nullptr){
            tempScope->printScope();
            tempScope = tempScope->getparentScope();
        }
    }
    bool Insert(string x, string y)
    {
        if(currentScope->Insert(x,y))
        {
            cout<<"Inserted in ScopeTable# "<<currentScope->getScopeAddress()<<" at position "<<currentScope->getHashIndex()<<"\n";
            return true;
        }
        else
        {
            return false;
        }
 
    }
    void EnterScope()
    {
 
        /*
        currentScope->incChild();
        scopes.push_back(*currentScope);
        currentScope = new ScopeTable(bucketSize);
        currentScope->setScopeAddress(parentScope->getScopeAddress(),parentScope->getChild());
        cout<<"ScopeTable with id "<<currentScope->getScopeAddress()<<" created\n";
        */
        if(this->currentScope == nullptr){
            this->currentScope = currentScope;
            currentScope->setScopeAddress(currentScope->getScopeAddress(),currentScope->getChild());
            currentScope->setparentScope(nullptr);
        }
        else{
            currentScope->setScopeAddress(currentScope->getScopeAddress(),currentScope->getChild());
            cout<<"ScopeTable with id "<<currentScope->getScopeAddress()<<" created\n";
 
        }
    }
    void ExitScope()
    {
        cout<<"ScopeTable with id "<<currentScope->getScopeAddress()<<" removed\n";
        /*
        delete currentScope;
        currentScope = new ScopeTable(bucketSize);
        currentScope = &scopes.back();
        scopes.pop_back();
        */
        if(currentScope->getparentScope()!=nullptr){
            currentScope = currentScope->getparentScope();
        }
    }
 
    bool removeSymbol(string sign)
    {
        SymbolInfo *temp = currentScope->LookUp(sign);
        if(temp != NULL)
        {
            currentScope->deleteSymbol(sign);
            return true;
        }
        else
        {
                cout<<"Not found\n";
            cout<<sign<<" not found\n"<<endl;
            return false;
        }
    }
 
    SymbolInfo* LookUp(string name)
    {
        SymbolInfo *temp =  currentScope->LookUp(name);
 
        if( temp == NULL )
        {
            int range = scopes.size();
 
            for(int i=range-1; i>=0; i--)
            {
                temp = scopes[i].LookUp(name);
                if( temp != NULL )
                {
                    return temp;
                }
            }
            cout<<"Not Found"<<endl;
        }
        else{
                return temp;
        }
    }
    string getAddress()
    {
        return currentScope->getScopeAddress();
    }
    string getPosition()
    {
        return "";  ///HAVE TO WORK HERE
    }
 
};
 
 
int main()
{
 
    ifstream in("input.txt");
    int len=0;
    //cin>>len;
    in>>len;
    SymbolTable S(len);
    string command, x,y;
    while(!in.eof())
    {
        //cin>>command;
        in>>command;
        cout<<command;
        if(command.compare("I")==0)            ///INSERT
        {
            //cin>>x>>y;
            in>>x>>y;
            cout<<x<<""<<y<<endl;
            if(S.Insert(x,y))
            {
 
            }
            else
            {
                cout<<"Already exists in current scope table\n";
            }
        }
        else if(command.compare("L")==0)        ///LOOKUP
        {
            //cin>>x;
            in>>x;
            S.LookUp(x);
        }
        else if(command.compare("D")==0)        ///DELETE
        {
            //cin>>x;
            in>>x;
            S.removeSymbol(x);
        }
        else if(command.compare("P")==0)        ///PRINT
        {
            //cin>>x;
            in>>x;
            cout<<x<<endl;
            if(x.compare("A")==0)
            {
                S.printAllScope();
            }
            else if(x.compare("C")==0)
            {
 
                S.printCurrentScope();
            }
        }
        else if(command.compare("S")==0)        ///ENTER SCOPE
        {
            S.EnterScope();
        }
        else if(command.compare("E")==0)        ///EXIT SCOPE
        {
 
            S.ExitScope();
        }
    }
    return 0;
}