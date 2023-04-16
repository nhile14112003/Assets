import React, {useEffect, useState} from 'react';
import {Text, StyleSheet, View, FlatList, TouchableOpacity} from 'react-native';
import {openDatabase} from 'react-native-sqlite-storage';
import ClassDetails from './ClassDetails';

const db = openDatabase({ name: 'classes.db'});

    
const Classes = ({navigation}) => {
    const [classes_array, setClassArray] = useState([]);
    
    useEffect(() => {
    
        db.transaction((tx) => {
            tx.executeSql('select * from classesTable', [], (tx, results)=>{
                    let arr = [];
                    for (let i = 0; i < results.rows.length; i++){
                      arr.push(results.rows.item(i));
                    }
                    setClassArray(arr);
                })
               
        })
    }, [])
     
    return(
    <View>
        <FlatList
              data={classes_array}
              keyExtractor={item => item.id}
              renderItem={({item}) => (
                <View style={styles.container}>
                <TouchableOpacity style={styles.button} onPress={()=> navigation.navigate('ClassDetails', {item:item})}>
                    <Text style={styles.text} > Id: {item.id} </Text>
                    <Text style={styles.text} > Name: {item.name} </Text>
                    <Text style={styles.text} > Students: {item.students}</Text>
                </TouchableOpacity>

             </View>
              )}
          />
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

  text: {
    color: '#000000',
    fontSize: 21,
    marginVertical: 5
  },

  button: {
    backgroundColor:'#CACACA',
    marginTop: 10,
    padding: 10
  },
    
  });
  


export default Classes;