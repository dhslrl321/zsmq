import reset from "styled-reset";
import { createGlobalStyle } from "styled-components";

const GlobalStyle = createGlobalStyle`
  ${reset}
  * {
    box-sizing: border-box;
  }
  body {
    font-family: -apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif;
    background: #F8F9FC;
    color: #525463;
  }
  a {
    color: inherit;
    text-decoration: none;
  }
  input, button {
    background-color: transparent;
    border: none;
    outline: none;
  }
  h1, h2, h3, h4, h5, h6{
    font-family:'Maven Pro', sans-serif;
  }
  ul, ol, li, a, input, select, textarea {
    margin: 0;
    padding: 0; 
    border: 0 none; 
  } 
  ul, ol, li {
    list-style: none;
  } 
  em, address {
    font-style: normal;
  } 
  img {
    border: 0 none; 
    font-size: 0;
    line-height: 0;
  } 
  sup {
    position: relative;
    top: 2px;
    font-size: 11px;
    line-height: 100%;
  } 
  
  /* @media only screen and (max-width: 768px) {
    body {
      font-size: 12px;
    }
  }
  @media only screen and (max-width: 576px) {
    body {
      font-size: 10px;
    }
  } */
`;

export default GlobalStyle;