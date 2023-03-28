import React, { Component, useState } from 'react';
import {StyleSheet, Text, TouchableOpacity, SafeAreaView, View, Image, FlatList} from 'react-native';

const App = () => {
  
  const [data, setData] = useState<any[]>([]);

  const fetchData = () => {
     
    return fetch('https://testnets-api.opensea.io/api/v1/assets?format=json')
      .then((response) => response.json())
      .then( data => {
          setData(data.assets)
      })
      .catch((error) => {
        console.error(error);
      });

  }
 

  const [selectedItem, setSelectedItem] = useState<any>(null);

  if(selectedItem != null){
    return (
      <View style={styles.container}>
        <Image style = {styles.stretch} source = {{uri: selectedItem.image_url}}/>
        <Text style={styles.text}> {selectedItem.name} </Text>
        <Text style={styles.text}> {selectedItem.description} </Text>
        <TouchableOpacity style={styles.button} onPress={()=> setSelectedItem(null)}>
          <Text style={styles.text}>Back</Text> 
          
        </TouchableOpacity>
      </View>
    )
  }

  return (
    <SafeAreaView style={styles.container}> 
      <View>
        <TouchableOpacity style={styles.button} onPress={fetchData}>
          <Text style={styles.text}>Load</Text>
          
        </TouchableOpacity>
        
          <FlatList
              data={data}
              renderItem={({item}) => (
                <TouchableOpacity onPress = { () => setSelectedItem(item)}>
                  <View style={styles.container}>
                      <Image style = {styles.stretch} source = {{uri: item.image_url}}/>
                      <Text style={styles.text}> {item.name} </Text>
                  </View>

                </TouchableOpacity>
              )}
              keyExtractor={item => item.id}
          />
        

        </View>
        
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    paddingTop: 30,
  },
  text: {
    color: '#000000',
    fontSize: 20
    
  },
  stretch: {
    marginTop: 5,
    width: 200,
    height: 200,
    resizeMode: 'stretch',
  },
  button: {
    alignItems: 'center',
    backgroundColor: '#87D0FF',
    padding: 10,
  },
});

export default App;