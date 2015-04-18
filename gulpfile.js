var gulp = require('gulp');

gulp.task('3pp', function () {
    gulp.src(['src/main/js_3pp/*.js','src/main/css_3pp/*.css'])
        .pipe(gulp.dest('build/resources/main/static'))
});

gulp.task('js', function () {
    gulp.src(['src/main/js/*.js','src/main/js/*.html'])
        .pipe(gulp.dest('build/resources/main/static'))
});

gulp.task('build', ['3pp', 'js']);

gulp.task('default', ['build']);
