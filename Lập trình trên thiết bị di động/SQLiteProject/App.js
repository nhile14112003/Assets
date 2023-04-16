import React, {Component, useState} from 'react';
import {Text, StyleSheet, View, TextInput, TouchableOpacity, Alert} from 'react-native';
import Login from './src/Login'
import Classes from './src/Classes'
import ClassDetails from './src/ClassDetails';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import {openDatabase} from 'react-native-sqlite-storage';

const db = openDatabase({ name: 'classes.db'});
db.transaction((tx) => {
  tx.executeSql('CREATE TABLE IF NOT EXISTS classesTable (id TEXT PRIMARY KEY, name TEXT NOT NULL,students INTEGER NOT NULL)');
  tx.executeSql('INSERT INTO classesTable VALUES (?,?,?)', ['class01', 'Class001', 8]);
  tx.executeSql('INSERT INTO classesTable VALUES (?,?,?)', ['class02', 'Class002', 6]);
  tx.executeSql('INSERT INTO classesTable VALUES (?,?,?)', ['class03', 'Class003', 7]);
  tx.executeSql('INSERT INTO classesTable VALUES (?,?,?)', ['class04', 'Class004', 12]);

});
db.transaction((tx) => {
  tx.executeSql('CREATE TABLE IF NOT EXISTS classDetailTable (id TEXT PRIMARY KEY, name TEXT NOT NULL, dob TEXT NOT NULL, image TEXT NOT NULL, classID TEXT NOT NULL)');
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student001', 'Nguyen Van A', '01/01/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class01']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student002', 'Tran Thi B', '01/02/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class01']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student003', 'Dao Van Loc', '01/03/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class01']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student004', 'Ngo Thi Linh', '12/01/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class01']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student005', 'Ngo Thi Thao', '12/01/2001', 'https://static.thenounproject.com/png/3492499-200.png', 'class01']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student006', 'Le Xuan Anh', '12/02/2003', 'https://static.thenounproject.com/png/3492499-200.png', 'class01']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student007', 'Le Xuan Tu', '12/02/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class01']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student008', 'Le Xuan Huy', '12/02/2004', 'https://static.thenounproject.com/png/3492499-200.png', 'class01']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student009', 'Le Xuan Truong', '12/03/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class02']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student010', 'Le Xuan Duc', '11/05/2002', 'https://static.thenounproject.com/png/3492499-200.png', 'class02']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student011', 'Le Thi Mai', '12/11/1999', 'https://static.thenounproject.com/png/3492499-200.png', 'class02']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student012', 'Truong Bich', '12/05/2001', 'https://static.thenounproject.com/png/3492499-200.png', 'class02']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student013', 'Le Hoai', '12/03/2002', 'https://static.thenounproject.com/png/3492499-200.png', 'class02']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student014', 'Le Bao Nhu', '08/03/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class02']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student015', 'Le Thi Lan', '09/03/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class03']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student016', 'Nguyen Cat Tuong', '10/03/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class03']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student017', 'Le Son', '08/04/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class03']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student018', 'Nguyen An', '09/12/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class03']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student019', 'Le Duc Hau', '20/03/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class03']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student020', 'Pham Le Quy Anh', '28/02/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class03']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student021', 'Pham Anh Duc', '08/09/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class03']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student022', 'Le Pham Hai ', '08/08/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class04']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student023', 'Dinh Nguyen Loc', '08/06/2001', 'https://static.thenounproject.com/png/3492499-200.png', 'class04']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student024', 'Ha Mien', '10/07/2002', 'https://static.thenounproject.com/png/3492499-200.png', 'class04']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student025', 'Le Van D', '10/07/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class04']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student026', 'Nguyen Minh Duy', '08/03/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class04']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student027', 'Luong Le', '14/02/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class04']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student028', 'Luong Long Vu', '25/04/1998', 'https://static.thenounproject.com/png/3492499-200.png', 'class04']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student029', 'Tran Doan Nhat', '30/04/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class04']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student030', 'Tran Y Vy', '29/05/1999', 'https://static.thenounproject.com/png/3492499-200.png', 'class04']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student031', 'Tran Yen Nhi', '21/10/2003', 'https://static.thenounproject.com/png/3492499-200.png', 'class04']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student032', 'Le Yen Ngoc', '22/10/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class04']);
  tx.executeSql('INSERT INTO classDetailTable VALUES (?,?,?,?,?)', ['student033', 'Nguyen Tran Ngoc', '08/07/2000', 'https://static.thenounproject.com/png/3492499-200.png', 'class04']);

});



const Stack = createNativeStackNavigator();

const App = () => {
    
    return(
        <NavigationContainer>
            <Stack.Navigator>
                <Stack.Screen name="Login" component={Login}/>
                <Stack.Screen name="Classes" component={Classes}/>
                <Stack.Screen name="ClassDetails" component={ClassDetails}/>
            </Stack.Navigator>
        </NavigationContainer>
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
    stretch: {
      marginTop: 5,
      width: 100,
      height: 100,
      resizeMode: 'stretch'
    },
    button: {
      backgroundColor: '#5B8CDB',
      marginTop: 10,
      alignItems: 'center',
      padding: 10
    },
    icon: {
        alignContent: 'center',
        width: 30,
        height: 30,
        resizeMode: 'stretch'
    }
  });
  


export default App;