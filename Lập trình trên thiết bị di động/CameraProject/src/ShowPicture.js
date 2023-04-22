import React from 'react';
import {View, Image, Button} from 'react-native';
const ShowPicture = ({navigation, route}) => {
  return(
  <View style={{flex: 1, padding: 10}}>
      <Image source={{uri: route.params.picture_source}} style={{flex:1, marginBottom:10}}/>
      <Button title="Back" onPress={() => navigation.navigate('Home')} />
  </View>
  )
}
export default ShowPicture;