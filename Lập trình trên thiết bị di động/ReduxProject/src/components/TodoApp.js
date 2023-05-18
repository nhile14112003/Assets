import React from 'react';
import { useSelector } from "react-redux";
import {StyleSheet, Text, TouchableOpacity, View, FlatList} from 'react-native';
import TodoItem from './ToDoItem';
const TodoApp = ({navigation, state}) => {
  const ToDoList = useSelector((state) => state.ToDoList.value);
  return (
      <View style={styles.container}>
      <View style={{flex: 1, padding: 5}}>
          <FlatList
            data={ToDoList}
            renderItem={({item, index}) => (
              <TouchableOpacity onPress={() => navigation.navigate('NewAndEdit', {item: item, index: index})}>
                <TodoItem ordinalNumber={index + 1} 
                          title={item.Title} descp={item.Description} 
                          time={item.Time} 
                          markAsDone={item.MarkAsDone}
                          />
              </TouchableOpacity>
            )}
            keyExtractor={(item, index) => index.toString()}//phải để thêm item dô ko là lỗi Two children with the same key in React}
            extraData={state}
          />
          
        </View>
       <TouchableOpacity style={styles.button} onPress={() => navigation.navigate('NewAndEdit', {item: {Title: '', Description: '', Time: '', MarkAsDone:false}, index: -1})}>
        <Text style={styles.text}> + </Text>
       </TouchableOpacity>
        </View>
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
  button: {
    
    alignItems: 'center',
    backgroundColor: '#87D0FF',
    padding: 10,
    width: 50,
    borderRadius: 10,
    marginStart: 320
  },
});

export default TodoApp;
