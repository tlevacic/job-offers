const defaultTheme = require('tailwindcss/defaultTheme')
module.exports = {
  future: {
    removeDeprecatedGapUtilities: true,
  },
  theme: {
    extend:{
      colors: {
        'darkCyan': 'hsl(180, 29%, 50%)',
        'background': 'hsl(180, 52%, 96%)',
        'filterTablets': 'hsl(180, 31%, 95%)',
        'darkGrayishCyan': 'hsl(180, 8%, 52%)',
        'veryDarkGrayishCyan': 'hsl(180, 14%, 20%)'
      },
      fontFamily: {
        display: ['Spartan', 'sans-serif'],
        body: ['Spartan', 'sans-serif'],
      }
    }
  }
};