var gulp = require('gulp');
var babel = require('gulp-babel');

var files_3pp = ['src/main/js_3pp/*.js', 'src/main/css_3pp/*.css'];
var js = ['src/main/js/*.js'];
var html = ['src/main/js/*.html'];
var all_files = js.concat(html).concat(files_3pp);

gulp.task('3pp', function () {
    gulp.src(files_3pp)
        .pipe(gulp.dest('build/resources/main/static'))
});

gulp.task('js', function () {
    gulp.src(js)
        .pipe(babel())
        .pipe(gulp.dest('build/resources/main/static'))
});

gulp.task('html', function () {
    gulp.src(html)
        .pipe(gulp.dest('build/resources/main/static'))
});

gulp.task('build', ['3pp', 'js', 'html']);

gulp.task('default', ['build']);

gulp.task('watch', ['build'], function () {
    gulp.watch(all_files, ['build']);
});

