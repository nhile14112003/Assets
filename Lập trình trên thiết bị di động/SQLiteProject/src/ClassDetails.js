import React, {useEffect, useState} from 'react';
import {Text, StyleSheet, View, FlatList, Image, Alert} from 'react-native';
import {openDatabase} from 'react-native-sqlite-storage';
const db = openDatabase({ name: 'classes.db'});

    
const ClassDetails = ({navigation, route}) => {
    const [students, setStudents] = useState([]);
    
    useEffect(() => {
    
        db.transaction((tx) => {
            tx.executeSql('select * from classDetailTable where classID = ?', [route.params.item.id], (tx, results)=>{
                    let arr = [];
                   
                    for (let i = 0; i < results.rows.length; i++){
                      arr.push(results.rows.item(i));
                    }
                    setStudents(arr);
                })
               
        })
    }, [])
    return(
        <View style={{flex:1}}>  
        <View style={[styles.container, {backgroundColor:'#CACACA'}]}>   
            <Text style={styles.text} > Id: {route.params.item.id} </Text>
            <Text style={styles.text} > Name: {route.params.item.name} </Text>
            <Text style={styles.text} > Students: {route.params.item.students} </Text>
            </View> 
            <View style={{flex:1, padding:5}}>
            <FlatList
              data={students}
              keyExtractor={item => item.id}
              renderItem={({item}) => (
                <View style={[styles.container,{flexDirection:'row', backgroundColor: '#5B8CDB'}]}>
                    <Image style = {styles.stretch} source = {{uri: item.image}}/>
                    <View>
                        <Text style={styles.text} > Id: {item.id} </Text>
                        <Text style={styles.text} > Name: {item.name} </Text>
                        <Text style={styles.text} > Students: {item.dob}</Text>
                    </View>
             </View>
              )}
          />
          </View>
          </View>
    )
}
const styles = StyleSheet.create({
  container: {
    marginTop: 10,
    jutifyContent: 'center',
    marginHorizontal: 20,
    padding: 8
  },

  text: {
    color: '#000000',
    fontSize: 21,
    marginVertical: 5
  },

  
  stretch: {
    marginTop: 5,
    width: 90,
    height: 100,
    resizeMode: 'stretch'
  },
  });
  


export default ClassDetails;