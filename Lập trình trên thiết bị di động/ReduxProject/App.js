import React, { Component, useState } from 'react';
import {StyleSheet, Text, TouchableOpacity, View, Image, FlatList} from 'react-native';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import TodoApp from './src/components/TodoApp'
import NewAndEdit from './src/components/NewAndEdit'
import { store } from './src/store/store';
import { Provider } from "react-redux";
const Stack = createNativeStackNavigator();
const App = () => {
  
  return (
    <NavigationContainer>
      <Stack.Navigator>
          <Stack.Screen name="ToDoApp" component={TodoApp}/>
          <Stack.Screen name="NewAndEdit" component={NewAndEdit}/>
      </Stack.Navigator>
    </NavigationContainer>
  )
};
export default () => {
  return (
    <Provider store={store}>
      <App />
    </Provider>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingVertical: 10,
    paddingHorizontal: 10
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
    width: 50,
    borderRadius: 10,
    marginStart: 320
  },
});
