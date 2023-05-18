import React, {useState} from 'react';
import {StyleSheet, Text, View, TouchableOpacity, TextInput, Image} from 'react-native';
import CheckBox from '@react-native-community/checkbox';
import DateTimePicker from '@react-native-community/datetimepicker';
import { useDispatch } from "react-redux";
import {Add, Edit} from '../store/ToDoSlice'
const Detail = ({navigation, route}) => {
  const dispatch = useDispatch();
  const [title, setTitle] = useState(route.params.item.Title);
  const [descp, setDescp] = useState(route.params.item.Description);
  const [time, setTime] = useState(route.params.item.Time);
  const [isSelected, setSelection] = useState(route.params.item.MarkAsDone);
  const [date, setDate] = useState(new Date());
  const [show, setShow] = useState(false);
  //thêm event dô để có thể chạy như bình thường
  const onChange = (event, selectedDate) => {
     const currentDate = selectedDate || date;
      setShow(false);//để false nha má
      setDate(currentDate);
      let tempDate = new Date(currentDate);
      let fDate = tempDate.getDate() + '/' + (tempDate.getMonth() + 1) + '/' + tempDate.getFullYear();
      setTime(fDate);
  }
    
    const AddTask = () => {
      if (route.params.index >= 0)
        dispatch(Edit(route.params.index, title, descp, time, isSelected));
      else
        dispatch(Add(title, descp, time, isSelected));
      navigation.navigate('ToDoApp')
   }
      return(
      <View style={{flex: 1, padding: 15}}>
        <View style={{flex: 1}}>
          <Text style={styles.text}> Title </Text>
          <TextInput style={styles.textInput} onChangeText={setTitle} value={title}/>
          <Text style={styles.text}> Description </Text>
          <TextInput style={styles.textInput} onChangeText={setDescp} value={descp}/>
          <Text style={styles.text}> Time </Text>
          <View style={styles.container}>
            <TextInput style={[styles.textInput, {flex: 1}]} value={time}/>
            <TouchableOpacity onPress={()=>setShow(true)}>
              <Image style={styles.icon} source={{uri: 'https://p7.hiclipart.com/preview/74/186/347/computer-icons-google-calendar-calendar-date-png-calendar-icon.jpg'}}/>
            </TouchableOpacity>
          </View>

          <View style={styles.container}>
              <Text style={styles.text}> Mark as done: </Text>
              <CheckBox value={isSelected} onValueChange={setSelection}/>
          </View>
        </View>
          <TouchableOpacity style={styles.button} onPress={()=> AddTask()}>
            <Text style={styles.text}> Save </Text>
          </TouchableOpacity>
          {show && (
        <DateTimePicker
          value={date}
          mode={'date'}
          display='default'
          onChange={onChange}
        />
      )}
      </View>
      
      )
  };

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  text: {
    color: '#000000',
    fontSize: 20
  },
  textInput: {
    color: '#000000',
    fontSize: 18,
    backgroundColor:'#D5D3D3',
    height: 40
  },
  button: {
    alignItems: 'center',
    backgroundColor: '#87D0FF',
    padding: 10,
    marginLeft: 5
  },
  icon: {
        alignContent: 'center',
        width: 30,
        height: 30,
        resizeMode: 'stretch'
   }
});

export default Detail;