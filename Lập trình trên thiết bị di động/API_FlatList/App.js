import React, {Component, useState} from 'react';
import {Text, StyleSheet, TouchableOpacity, View, FlatList, Image} from 'react-native';

import { NavigationContainer, DarkTheme, DefaultTheme} from '@react-navigation/native';
import { createBottomTabNavigator} from '@react-navigation/bottom-tabs';

const Tab = createBottomTabNavigator();

const Products = [
    {id:'1', image:'https://www.shutterstock.com/image-photo/mountains-under-mist-morning-amazing-260nw-1725825019.jpg', price:50000, name:'item1'},
    {id:'2', image:'https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg', price:50000, name:'item2'},
]



const App = () => {
    const [isDarkTheme, setIsDarkTheme] = useState(false);
    const text_Color = isDarkTheme? '#FFFFFF':'#000000'
    const [favoritesIds, setFavoritesIds] = useState([]);
    const addFavorite = (item) => {
        if (favoritesIds.indexOf(item) < 0)
            setFavoritesIds([...favoritesIds, item])
        else{
            setFavoritesIds(favoritesIds.filter(favoritesId => favoritesId !== item))
        }
    }

    const FavoritesScreen = ({navigation}) => {
        return(
            <View>
                <FlatList
                  data={Products.filter(item => favoritesIds.includes(item.id))}
                  renderItem={({item}) => (
                    <View style={styles.container}>
                        <Image style = {styles.stretch} source = {{uri: item.image}}/>
                        <View style={{margin:25}}>
                            <Text style={[styles.text,{color:text_Color}]}> {item.name} </Text>
                            <Text style={[styles.text,{color:text_Color}]}> {item.price} </Text>
                        </View>
                        <TouchableOpacity style={{flex:1}} onPress={() => addFavorite(item.id)}>
                            <View style={{flex:1, alignItems:'flex-end', marginTop:40, marginRight:20}}>
                                <Image style = {styles.icon} source = {{uri:favoritesIds.indexOf(item.id) >= 0 ?'https://cdn-icons-png.flaticon.com/512/8294/8294893.png':'https://cdn-icons-png.flaticon.com/512/9542/9542129.png'}} />
                            </View>
                        </TouchableOpacity>
                    </View>
                  )}
                  keyExtractor={item => item.id}
                />
            </View>
        )
    }

    
    const SettingsScreen = ({navigation}) =>{
        
        return(
            <View style={styles.container}>
                <Text style={[styles.text,{color:text_Color}]}> Theme: </Text>
                <TouchableOpacity style={styles.button} onPress={()=>setIsDarkTheme(false)}> 
                    <Text style={[styles.text,{color:text_Color}]}> Light </Text>
                </TouchableOpacity>
                <TouchableOpacity style={styles.button} onPress={()=>setIsDarkTheme(true)}> 
                    <Text style={[styles.text,{color:text_Color}]}> Dark </Text>
                </TouchableOpacity>
                <TouchableOpacity/>
    
            </View>
        )
    }
    
    const HomeScreen = ({navigation}) => {
        return(
            <View>
                <FlatList
                  data={Products}
                  renderItem={({item}) => (
                    <View style={styles.container}>
                        <Image style = {styles.stretch} source = {{uri: item.image}}/>
                        <View style={{margin:25}}>
                            <Text style={[styles.text,{color:text_Color}]}> {item.name} </Text>
                            <Text style={[styles.text,{color:text_Color}]}> {item.price} </Text>
                        </View>
                        <TouchableOpacity style={{flex:1}} onPress={() => addFavorite(item.id)}>
                            <View style={{flex:1, alignItems:'flex-end', marginTop:40, marginRight:20}}>
                                <Image style = {styles.icon} source = {{uri:favoritesIds.indexOf(item.id) >= 0 ?'https://cdn-icons-png.flaticon.com/512/8294/8294893.png':'https://cdn-icons-png.flaticon.com/512/9542/9542129.png'}} />
                            </View>
                        </TouchableOpacity>
                    </View>
                  )}
                  keyExtractor={item => item.id}
                />
            </View>
        )
    }

    return (
        <NavigationContainer theme={isDarkTheme? DarkTheme:DefaultTheme}>
            <Tab.Navigator>
                <Tab.Screen 
                    name="Home" 
                    component={HomeScreen} 
                    options={{
                        tabBarIcon: () =>
                            <Image 
                                style={styles.icon} 
                                source={require('./images/home.png')}
                            />
                        
                    }}
                />
                <Tab.Screen 
                    name="Favorites" 
                    component={FavoritesScreen} 
                    options={{
                        tabBarIcon: () =>
                            <Image 
                                style={styles.icon} 
                                source={require('./images/favorites.png')}
                            />
                        
                    }}
                />
                <Tab.Screen 
                    name="Settings" 
                    component={SettingsScreen} 
                    options={{
                        tabBarIcon: () =>
                            <Image 
                                style={styles.icon} 
                                source={require('./images/settings.png')}
                            />
                        
                    }}
                />
            </Tab.Navigator>
        </NavigationContainer>

    );
    
}



const styles = StyleSheet.create({
    container: {
      flex: 1,
      flexDirection: 'row',
      marginTop: 10,
      marginLeft: 20
    },

    
    text: {
      color: '#000000',
      fontSize: 20
    },
    stretch: {
      marginTop: 5,
      width: 100,
      height: 100,
      resizeMode: 'stretch'
    },
    button: {
      alignItems: 'center',
      backgroundColor: 'lightblue',
      height: 35,
      width: 55,
      marginLeft: 20
    },
    icon: {
        alignContent: 'center',
        width: 30,
        height: 30,
        resizeMode: 'stretch'
    }
  });
  


export default App;
