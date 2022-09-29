import React from "react";

import Header from "../../components/Header/Header";
import Gallery from "../../components/post/Gallery";

import styles from "../../styles/Top.module.css";

function Top(props) {
  return (
    <>
      <Header />
      <div className={styles.galleryWrap}>
        <Gallery />
      </div>
    </>
  );
}

export default Top;
