import React from 'react';
import {StyleSheet, Text, View, TouchableOpacity} from 'react-native';
import CheckBox from '@react-native-community/checkbox';
import { ChangeCheckBoxValue, Delete } from '../store/ToDoSlice';
import { useDispatch } from 'react-redux';
const TodoItem = (props) => {
   const dispatch = useDispatch();
   //chỉ cần ko cùng onPress mà TouchableOpacity con nằm trong cha là được hoặc là disabled cái cha để mỗi thằng con hoặc cứ mỗi TouchableOcapcity bỏ view vô
    return (
        <View style={[styles.container, {backgroundColor:'#C3D1FD', borderRadius: 10, padding: 15, marginBottom: 10}]}>
            <View style={{flex: 1}}>
                <View style={styles.container}>
                    <Text style={styles.text}> #{props.ordinalNumber} </Text>
                    <Text style={styles.text}> {props.title} </Text>
                </View>
            <Text style={styles.text}> {props.descp} </Text>
            <Text style={styles.text}> {props.time} </Text>
            </View>
            <CheckBox value={props.markAsDone} onValueChange={()=>dispatch(ChangeCheckBoxValue(props.ordinalNumber - 1, props.markAsDone))}/>
            <View>
            <TouchableOpacity style={styles.button} onPress={()=>dispatch(Delete(props.ordinalNumber - 1))}> 
              <Text style={styles.text}> Delete </Text>
            </TouchableOpacity>
            </View>
        </View>
    );
};

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    alignItems: 'center'
  },
  text: {
    color: '#000000',
    fontSize: 20
  },
  button: {
    alignItems: 'center',
    backgroundColor: '#E31414',
    borderRadius: 5,
    padding: 5,
    marginLeft: 5
  },
});

export default TodoItem;