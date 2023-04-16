import React, {useState} from 'react';
import {Text, StyleSheet, View, TextInput, TouchableOpacity, Alert} from 'react-native';
import {openDatabase} from 'react-native-sqlite-storage';


const db = openDatabase({ name: 'classes.db'});
db.transaction((tx) => {
    tx.executeSql('CREATE TABLE IF NOT EXISTS loginTable (username TEXT PRIMARY KEY, password TEXT NOT NULL)');
    tx.executeSql('INSERT INTO loginTable VALUES (?,?)', ['lannhi1411', 'ABC']);
    tx.executeSql('INSERT INTO loginTable VALUES (?,?)', ['hami0907', 'DEF']);
  
  });
    
    
const Login = ({navigation}) => {
    
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    
    const loginButtonClick = () => {
        if (username == '' || password == '')
            Alert.alert('Mời bạn nhập đầy đủ tên đăng nhập và mật khẩu');
        else{
            db.transaction((tx) => {
                tx.executeSql('select * from loginTable where username=?', [username], (tx, results)=>{
                    const len = results.rows.length;
                    if(len == 0){
                        Alert.alert('Tài khoản không tồn tại');
                    }
                    else{
                        if (results.rows.item(0).password == password){
                            Alert.alert('Đăng nhập thành công');Alert.alert('Đăng nhập thành công');
                            navigation.navigate('Classes');
                        }
                        else
                            Alert.alert('Sai mật khẩu');
                    }
                });
              
            });
            
        };
        }
    

 
    return(
    
        <View style={styles.container}>
            <Text style={[styles.text,{marginTop: 60, fontSize: 30, textAlign:'center'}]}> LOGIN </Text>
            <Text style={[styles.text,{marginTop: 30}]}> Username: </Text>
            <TextInput style={styles.textInput} onChangeText={setUsername} value={username}/>
            <Text style={styles.text}> Password: </Text>
            <TextInput style={styles.textInput} onChangeText={setPassword} value={password}/>
            <TouchableOpacity style={styles.button} onPress={loginButtonClick} >
                <Text style={styles.text}> Login </Text>
            </TouchableOpacity>
        </View>
    )
}
const styles = StyleSheet.create({
    container: {
        flex: 1,
        marginTop: 10,
        jutifyContent: 'center',
        paddingHorizontal: 40
    },
    textInput: {
        backgroundColor: '#D9D9D9',
        fontSize: 18,
    
    },
    
    text: {
      color: '#000000',
      fontSize: 21
    },
    
    button: {
      backgroundColor: '#5B8CDB',
      marginTop: 10,
      alignItems: 'center',
      padding: 10
    },
    
  });
  


export default Login;