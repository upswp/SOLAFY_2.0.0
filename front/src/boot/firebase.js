import firebase from "firebase/app";
import "firebase/auth";
import firebaseConfig from "../firebaseConfig"

let firebaseApp = firebase.initializeApp(firebaseConfig);
let firebaseAuth = firebaseApp.auth();

export { firebaseAuth };