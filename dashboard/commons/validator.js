export const regexNumberAndEnglishAndHyphen = (provided) => {
  const regex = /^[A-Za-z\\d_-]+$/;
  return regex.test(provided);
}