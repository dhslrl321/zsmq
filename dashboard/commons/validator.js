export const regexNumberAndEnglishAndHyphen = (provided) => {
  const regex = /^[A-Za-z0-9/g\\d_-]+$/;
  return regex.test(provided);
};
